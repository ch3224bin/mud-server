package com.jeff.mud.global.security.user.model;

import com.jeff.mud.global.account.domain.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
    if(AuthProvider.google.toString().equalsIgnoreCase(registrationId)) {
      return new GoogleOAuth2UserInfo(attributes);
    } else {
      throw new RuntimeException("Sorry! Login with " + registrationId + " is not supported yet.");
    }
  }
}
