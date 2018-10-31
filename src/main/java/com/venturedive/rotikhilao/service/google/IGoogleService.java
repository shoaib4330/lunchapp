package com.venturedive.rotikhilao.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.util.Map;

public interface IGoogleService {

    String saveNewUser(GoogleIdToken.Payload payload);

    Boolean checkUserExistence(GoogleIdToken.Payload payload);

    String checkUserExistence(Map <String, Object> map) throws Exception;
}
