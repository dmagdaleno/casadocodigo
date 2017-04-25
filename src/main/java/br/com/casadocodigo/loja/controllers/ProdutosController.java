package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;

    @RequestMapping("/form")
    public ModelAndView form(){
    	ModelAndView model = new ModelAndView("produtos/form");
    	model.addObject("tipos", TipoPreco.values());
        return model;
    }

    @RequestMapping(method=RequestMethod.POST)
    public String gravar(Produto produto){
        System.out.println(produto);
        produtoDao.gravar(produto);
        return "produtos/ok";
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView listar(){
    	ModelAndView modelAndView = new ModelAndView("produtos/lista");
    	modelAndView.addObject("produtos", produtoDao.listar());
    	return modelAndView;
    }

}