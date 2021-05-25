package com.jeff.mud.global.security.user.model;

import com.jeff.mud.global.account.domain.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {
  private Long id;
  private String name;
  private String username;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;
  private Map<String, Object> attributes;

  public UserPrincipal(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes) {
    this.id = id;
    this.name = username;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.attributes = attributes;
  }

  public static UserPrincipal create(Account account, Map<String, Object> attributes) {
    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    return new UserPrincipal(
      account.getId(),
      account.getUsername(),
      account.getEmail(),
      account.getPassword(),
      authorities,
      attributes
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
