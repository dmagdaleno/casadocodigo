package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView gravar(Produto produto, RedirectAttributes redirectAttributes){
        System.out.println(produto);
        produtoDao.gravar(produto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");
        return new ModelAndView("redirect:produtos");
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView listar(){
    	ModelAndView modelAndView = new ModelAndView("produtos/lista");
    	modelAndView.addObject("produtos", produtoDao.listar());
    	return modelAndView;
    }

}