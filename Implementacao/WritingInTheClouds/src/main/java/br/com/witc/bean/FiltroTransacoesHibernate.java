package br.com.witc.bean;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcelo.lima
 */
public class FiltroTransacoesHibernate implements Filter {
    private final static String FILTER_APPLIED = "_security_filter_applied";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession sessaoHTTP = req.getSession(true);
        req.getPathInfo();
        String paginaAtual = new String(req.getRequestURL());
        
        // dont filter index.xhtml because otherwise an endless loop.	
        if ((request.getAttribute(FILTER_APPLIED) == null) && (!paginaAtual.endsWith("index.xhtml"))) {
            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
            // If the session bean is not null get the session bean property
            // username.
            String nome = null;
            if ((sessaoHTTP.getAttribute("autenticarBean")) != null) {
                AutenticarBean bean = ((br.com.witc.bean.AutenticarBean)(sessaoHTTP.getAttribute("autenticarBean")));
                nome = bean.getNomeUsuario();
            }
            if (nome == null) {
                // redireciona para a pagina de login
                resp.sendRedirect("/WITC/faces/index.xhtml");
                return;
            }            
        }                        

        TransacaoWitc transacaoCorrente = (TransacaoWitc) sessaoHTTP.getAttribute("transacaoCorrente");
        if(transacaoCorrente != null) {
            transacaoCorrente.iniciarRequisicao();
        }
        
        chain.doFilter(request, response);
        if(transacaoCorrente != null) {
            transacaoCorrente.finalizarRequisicao();
        }                      
    }

    @Override
    public void destroy() {
       
    }
    
}