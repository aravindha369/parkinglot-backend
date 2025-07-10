package com.centralized.parking.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.centralized.parking.utility.JwtUtility;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	private final UserDetailsService userDetailsService;
    private final JwtUtility jwtUtil;
    
    public JwtFilter(JwtUtility jwtUtil, @Lazy UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException, java.io.IOException {

	    String authHeader = request.getHeader("Authorization");
	    String jwtToken = null;
	    String username = null;

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        jwtToken = authHeader.substring(7);

	        try {
	            username = jwtUtil.extractUsername(jwtToken);
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Invalid or expired token");
	            return;
	        }
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	        if (jwtUtil.validateToken(jwtToken, userDetails)) {
	            UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	    }

	    filterChain.doFilter(request, response);
	}
}
