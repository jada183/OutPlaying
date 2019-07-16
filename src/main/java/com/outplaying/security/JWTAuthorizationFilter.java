package com.outplaying.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager, JwtConfig jwtConfig ) {
		
		super(authManager);
		this.jwtConfig = jwtConfig;
	}

	private final JwtConfig jwtConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(jwtConfig.getHeader());

		if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(jwtConfig.getHeader());
		Claims claims =Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes())
				.parseClaimsJws(token.replace(jwtConfig.getPrefix(), "")).getBody();
		if (token != null) {
			String user = claims.getSubject();
			@SuppressWarnings("unchecked")
			List<String> authorities =(List<String>) claims.get("authorities");
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			}
			return null;
		}
		return null;
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		System.err.println("authenticate");
	}
}
