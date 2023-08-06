package com.blog_Application.Service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

 /*   This method is called when a request is unauthenticated, and it is responsible for sending
  an appropriate response to the client.
  saaf sabdo m-->JB KOI Banda bager token k api hit karega use yeah response mil jayega
  */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {


        // Customize the response to the client for authentication failure.
        // For example, you can set appropriate headers and response status.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
