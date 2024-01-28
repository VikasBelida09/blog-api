package com.example.blogapi.jwt;

import com.example.blogapi.services.CustomUserDetailsService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username=null;
        String token=null;
        try {
            if(authHeader!=null && authHeader.startsWith("Bearer")){
                token=authHeader.substring(7);
                username=this.jwtHelper.extractUserName(token);
            }
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails user= customUserDetailsService.loadUserByUsername(username);

                if(this.jwtHelper.isTokenValid(token,user)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }
        catch (MalformedJwtException ex){
            System.out.println(ex.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
