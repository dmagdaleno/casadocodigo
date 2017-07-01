<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


  <header id="layout-header">
		<div class="clearfix container">
			<a href="${contextPath}" id="logo">
			</a>
			<div id="header-content">
				<nav id="main-nav">
					
					<ul class="clearfix">
						<security:authorize access="isAuthenticated()">
							<li><a href="${s:mvcUrl('PC#listar').build() }" rel="nofollow">Listagem de Produtos</a></li>
						</security:authorize>
						<security:authorize access="hasRole('ROLE_ADMIN')">
	    					<li><a href="${s:mvcUrl('PC#form').build() }" rel="nofollow">Cadastro de Produtos</a></li>
						</security:authorize>
						<li><a href="${s:mvcUrl('CCC#itens').build()}" rel="nofollow">Carrinho (${carrinhoCompras.quantidade})</a></li>
						<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre Nós</a></li>
						<li><a href="/pages/perguntas-frequentes" rel="nofollow">Perguntas Frequentes</a></li>
					</ul>
				</nav>