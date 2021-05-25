package com.jeff.mud.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app")
@Getter @Setter
public class AppProperties {
  private Auth auth;
  private OAuth2 oauth2;

  @Getter @Setter
  public static class Auth {
    private String tokenSecret;
    private long tokenExpirationMsec;
  }

  @Getter @Setter
  public static class OAuth2 {
    private List<String> authorizedRedirectUris;
  }
}
