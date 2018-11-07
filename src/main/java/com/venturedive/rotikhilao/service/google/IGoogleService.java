package com.venturedive.rotikhilao.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.venturedive.rotikhilao.DTO.UserTokenResponseDto;

import java.util.Map;

public interface IGoogleService {

    UserTokenResponseDto saveNewUser(GoogleIdToken.Payload payload);

    Boolean checkUserExistence(GoogleIdToken.Payload payload);

    UserTokenResponseDto checkUserExistence(Map <String, Object> map) throws Exception;
}
