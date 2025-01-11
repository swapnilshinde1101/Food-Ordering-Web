package com.foodordering.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Get the JWT token from the request header
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		// If JWT token exists
		if (jwt != null && jwt.startsWith("Bearer ")) {
			jwt = jwt.substring(7); // Remove "Bearer " prefix

			try {
				// Secret key used to validate JWT token
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRE_KEY.getBytes());
				
				// Parse the JWT token and get claims
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

				// Extract email and authorities (roles) from claims
				String email = claims.getSubject();  // Assuming "email" is stored as the subject
				String authorities = String.valueOf(claims.get("authorities"));

				// Convert roles to GrantedAuthority list
				List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

				// Create an authentication token
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(email, null, auth);
				
				// Set the authentication context
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				// If JWT parsing fails, throw an exception
				throw new BadCredentialsException("Invalid token....");
			}
		}

		// Continue with the filter chain
		filterChain.doFilter(request, response);
	}
}