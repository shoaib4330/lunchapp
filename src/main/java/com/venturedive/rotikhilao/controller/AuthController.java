package com.venturedive.rotikhilao.controller;

//import com.venturedive.rotikhilao.filters.OpenIdConnectFilter;
import com.venturedive.rotikhilao.DTO.LoginDto;
import com.venturedive.rotikhilao.configuration.GoogleTokenVerifier;
import com.venturedive.rotikhilao.DTO.UserTokenResponseDto;
import com.venturedive.rotikhilao.service.vendor.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    public OAuth2RestOperations restTemplate;

    @Autowired
    GoogleTokenVerifier googleTokenVerifier;
    @Autowired
    VendorService vendorService;


    @PostMapping(value="/customerlogin")
    public UserTokenResponseDto authenticateUser(@RequestBody @NotNull String body) throws Exception {
        log.info(body);
        return googleTokenVerifier.verifyIdToken(body);

    }

    @PostMapping(value = "/vendorLogin")
    public UserTokenResponseDto authenticateVendor(@RequestBody @NotNull LoginDto loginDto ){
        return vendorService.authenticateVendor(loginDto);
    }


}
