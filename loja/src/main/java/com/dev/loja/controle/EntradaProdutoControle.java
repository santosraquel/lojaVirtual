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

    private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>(); 

    @Autowired
    private EntradaProdutoRepositorio entradaProdutoRepositorio;
    
    @Autowired
    private EntradaItensRepositorio entradaItensRepositorio;

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @GetMapping("/administrativo/entrada/cadastrar")
    public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens){
        ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
        mv.addObject("entrada", entrada);
        mv.addObject("listaEntradaItens", this.listaEntrada);
        mv.addObject("entradaItens", entradaItens);
        mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
        mv.addObject("listaProdutos", produtoRepositorio.findAll());
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
    public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens ) {
        
        if(acao.equals("itens")){
            this.listaEntrada.add(entradaItens);
        }else if(acao.equals("salvar")){
            entradaProdutoRepositorio.saveAndFlush(entrada);
            for(entradaItens it:listaEntrada){
                it.setEntrada(entrada);
                entradaItensRepositorio.saveAndFlush(it);
                Optional<Produto> produto = produtoRepositorio.findById(it.getProduto().getId());
                Produto produto = prod.get();
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
                produto.setvalorVenda(it.getvalorVenda());
                produtoRepositorio.saveAndFlush(produto);
                this.listaEntrada = new ArrayList<>();
            }
            return cadastrar(new EntradaProduto(), new EntradaItens());
        }
        System.out.println(this.listaEntrada.size());


        return cadastrar(entrada, new EntradaItens());
    }
}
