package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender sender;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
		return () -> {
			String url = "http://book-payment.herokuapp.com/payment";
			String response;
			
			try{
				response = restTemplate.postForObject(url, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
				enviaEmailCompraProduto(usuario);
			} catch (HttpClientErrorException e) {
				response = "Valor maior que o permitido";
			}
			
			model.addFlashAttribute("message", response);
			System.out.println("Resposta: " + response);
			
			return new ModelAndView("redirect:/produtos");
		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
	    SimpleMailMessage email = new SimpleMailMessage();
	    email.setSubject("Compra finalizada com sucesso");
	    email.setTo(usuario.getEmail());
	    email.setText("Compra aprovada com sucesso no valor de " + carrinhoCompras.getTotal());
	    email.setFrom("compras@casadocodigo.com.br");
	    
	    sender.send(email);
	}
}
