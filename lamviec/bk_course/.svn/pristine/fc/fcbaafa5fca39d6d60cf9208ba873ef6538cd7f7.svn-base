package vn.edu.hcmut.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.filter.GenericFilterBean;

public class ConfigFilter extends GenericFilterBean {
	private String BAO_TRI = "1";
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (BAO_TRI.equalsIgnoreCase("0")) {
			chain.doFilter(req, res);
		}else {
			this.redirectStrategy.sendRedirect((HttpServletRequest) req,(HttpServletResponse) res, "/");
		}
		
	}

}
