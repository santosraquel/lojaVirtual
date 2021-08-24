package com.dev.loja.controle;

import java.util.Optional;

import javax.validation.Valid;

import com.dev.loja.modelos.Produto;
import com.dev.loja.repositorios.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EntradaProdutoControle {

    @Autowired
    private EntradaProdutoRepositorio entradaProdutoRepositorio;
    
    @Autowired
    private EntradaItensRepositorio entradaItensRepositorio;

    @GetMapping("/administrativo/entrada/cadastrar")
    public ModelAndView cadastrar(EntradaProduto entrada, List<EntradaItens> listaEntradaItens, EntradaItens entradaItens){
        ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
        mv.addObject("entrada", entrada);
        mv.addObject("listaEntradaItens", listaEntradaItens);
        mv.addObject("entradaItens", entradaItens);
        return mv;
    }
    
    // @GetMapping("/administrativo/produtos/listar")
    // public ModelAndView listar(){
    //     ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
    //     mv.addObject("listaProdutos", produtoRepositorio.findAll());
    //     return mv;
    // }

    // @GetMapping("/administrativo/produtos/editar/{id}")
    // public ModelAndView editar(@PathVariable("id") Long id){
    //     Optional<Produto> produto = produtoRepositorio.findById(id);
    //     return cadastrar(produto.get());
    // }
    
    // @GetMapping("/administrativo/produtos/remover/{id}")
    // public ModelAndView remover(@PathVariable("id") Long id){
    //     Optional<Produto> produto = produtoRepositorio.findById(id);
    //     produtoRepositorio.delete(produto.get());
    //     return listar();
    // }

    @PostMapping("/administrativo/entrada/salvar")
    public ModelAndView salvar(String acao, EntradaProduto entrada, List<EntradaItens> listaEntrada, EntradaItens entradaItens ) {
        
        if(acao.equals("itens")){
            listaEntrada.add(entradaItens);

        }


        return cadastrar(entrada, listaEntrada, new EntradaItens());
    }
}
