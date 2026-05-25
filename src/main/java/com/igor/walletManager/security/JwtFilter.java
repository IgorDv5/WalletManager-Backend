package com.igor.walletManager.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.igor.walletManager.entity.User;
import com.igor.walletManager.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserRepository userRepository;


	 @Override
	    protected void doFilterInternal(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            FilterChain filterChain)
	            throws ServletException, IOException {

	        String path = request.getServletPath();

	        // IGNORA AUTH
	        if (path.startsWith("/auth")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        String header = request.getHeader("Authorization");

	        if (header == null || !header.startsWith("Bearer ")) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return;
	        }

	        String token = header.substring(7);

	        if (!jwtService.isValid(token)) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return;
	        }

	        String email = jwtService.extractEmail(token);

	        User user = userRepository.findByEmail(email)
	                .orElseThrow();

	        UsernamePasswordAuthenticationToken auth =
	                new UsernamePasswordAuthenticationToken(
	                        user,
	                        null,
	                        List.of(new SimpleGrantedAuthority(
	                                "ROLE_" + user.getRole().name()
	                        ))
	                );

	        SecurityContextHolder.getContext().setAuthentication(auth);

	        filterChain.doFilter(request, response);
	    }

}
