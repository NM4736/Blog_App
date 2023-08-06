package com.blog_Application.Service;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // 1. get Token
        String requestToken = request.getHeader("Authorization");

        System.out.println(requestToken);

        String username = null;
        String token = null;

        if (request != null && requestToken!=null && requestToken.startsWith("Bearer"))
        {
           token = requestToken.substring(7);
           try {
             username=  this.jwtTokenHelper.getUserNameFromToken(token);
           }
           catch(IllegalArgumentException e)
           {
               System.out.println("Unable to get Jwt token");
           }
           catch (MalformedJwtException e)
           {
               System.out.println("Invalid Jwt");
           }
           catch(Exception e)
           {
               System.out.println(e.getMessage());
           }
        }
        else
        {
            System.out.println("Jwt token does not begin with Bearer");
        }

        // once we get token , then now validate token

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

            UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token,userDetails))
            {
                // SB SHI chal rha h
                //authenticatoin required
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else
            {
                System.out.println("Invalid jwt token");
            }
        }
        else {
                System.out.println("username is null");
        }

        // filter request
        filterChain.doFilter(request,response);
    }
}
