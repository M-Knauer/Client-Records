package com.marcelo.main.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marcelo.main.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository repo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = retriveToken(request);
		
		if (token != null) {
			var subject = tokenService.getSubject(token);
			var user = repo.findByLogin(subject);
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String retriveToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		
		if (authHeader != null) {
			return authHeader.replace("Bearer ", "");
		}
		
		return null;
	}

}
