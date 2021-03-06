package com.udemy.compras.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.compras.domain.ClienteService;
import com.udemy.compras.domain.Compra;
import com.udemy.compras.domain.CompraService;
import com.udemy.compras.domain.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CompraQueryGraphql implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public Compra compra(Long id){
        return compraService.findById(id);
    }

    public List<Compra> getCompras(){
        return compraService.findAll();
    }

    public Compra saveCompra(CompraInput input){
        ModelMapper m = new ModelMapper();
        Compra c = m.map(input, Compra.class);

        c.setData(new Date());
        c.setCliente(clienteService.findById(input.getClienteId()));
        c.setProduto(produtoService.findById(input.getProdutoId()));

        return compraService.save(c);
    }

    public Boolean deleteCompra(Long id){
        return compraService.deleteById(id);
    }
}
