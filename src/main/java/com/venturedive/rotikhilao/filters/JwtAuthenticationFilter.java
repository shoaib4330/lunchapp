package com.venturedive.rotikhilao.filters;

import com.venturedive.rotikhilao.configuration.JwtTokenProvider;
import com.venturedive.rotikhilao.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;


    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().matches("/api/auth(.*)")){
            filterChain.doFilter(request, response);
        }
        else if(request.getRequestURI().matches("/api/(.*)")){
            try {
                String jwt = getJwtFromRequest(request);

                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    logger.info("User signed in: " + tokenProvider.getUserIdFromJWT(jwt));
                } else {
                    throw new ServletException("unable to authenticate user");
                }

            } catch (Exception ex) {
                throw new ApplicationException("Unable to authenticate user");
            }
            filterChain.doFilter(request, response);
        }
        else {
            filterChain.doFilter(request, response);
        }

    }


    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) ) {
            return bearerToken;
        }
        return null;
    }

}
