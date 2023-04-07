package com.marcelo.main.security.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = retriveToken(request);
		
		System.out.println(token);
		
		filterChain.doFilter(request, response);
		
	}

	private String retriveToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		
		if (authHeader == null) {
			throw new RuntimeException("Token não enviado");
		}
		
		return authHeader.replace("Bearer ", "");
	}

}
