package com.jeff.mud.global.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  public OAuth2AuthenticationSuccessHandler() {
    super("http://localhost:3000/#/");
  }
}
