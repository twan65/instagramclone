package com.anveloper.instagramclone.common.config.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtAuthFilter extends GenericFilter {

  private JwtAuthProcessor jwtAuthProcessor;

  public JwtAuthFilter(JwtAuthProcessor jwtAuthProcessor) {
    this.jwtAuthProcessor = jwtAuthProcessor;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {

    try {
      Authentication authentication = this.jwtAuthProcessor.authenticate(
          (HttpServletRequest) request);
      if (authentication != null) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        SecurityContextHolder.clearContext();
      }
    } catch (Exception e) {
      ((HttpServletResponse) response).sendError(HttpStatus.SC_UNAUTHORIZED);
      response.getWriter().print(e);
      response.setContentType("application/json");
      SecurityContextHolder.clearContext();
      e.printStackTrace();
      return;
    }

    filterChain.doFilter(request, response);
  }
}
