package com.mistergamarra.shopnow.ordersvc.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {


    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);
    public static final String X_AUTH_USER = "x-auth-user";
    public static final String X_AUTH_ROLE = "x-auth-user-role";

    public static Authentication getAuthentication(HttpServletRequest request) {
        String authUser =  request.getHeader(X_AUTH_USER);
        LOG.info("trying {} : {}" , X_AUTH_USER,authUser);
        if(authUser == null){
            throw new BadCredentialsException("Invalid API Key");
        }
        String authUserRole =  request.getHeader(X_AUTH_ROLE);
        LOG.info("trying {} : {}" , X_AUTH_ROLE,authUserRole);
        if(authUserRole == null){
            throw new BadCredentialsException("Invalid API Key");
        }


        return new ApiKeyAuthentication(authUser, AuthorityUtils.NO_AUTHORITIES);
    }
}