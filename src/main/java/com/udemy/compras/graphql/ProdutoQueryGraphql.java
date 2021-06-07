package com.udemy.compras.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.compras.domain.Produto;
import com.udemy.compras.domain.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoQueryGraphql implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ProdutoService produtoService;

    public Produto produto(Long id){
        return produtoService.findById(id);
    }

    public List<Produto> produtos(){
        return produtoService.findAll();
    }

    public Produto saveProduto(Long id, String nome, double valor){

        Produto produto = new Produto(id, nome, valor);
        return produtoService.save(produto);
    }

    public Boolean deleteProduto(Long id){
        return produtoService.deleteById(id);
    }
}
