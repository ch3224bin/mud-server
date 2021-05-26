package com.jeff.mud.global.security.user.service;

import com.jeff.mud.global.account.dao.AccountRepository;
import com.jeff.mud.global.account.domain.Account;
import com.jeff.mud.global.account.domain.AuthProvider;
import com.jeff.mud.global.security.user.model.OAuth2UserInfo;
import com.jeff.mud.global.security.user.model.OAuth2UserInfoFactory;
import com.jeff.mud.global.security.user.model.UserPrincipal;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final AccountRepository accountRepository;

  public CustomOAuth2UserService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
    if(!StringUtils.hasLength(oAuth2UserInfo.getEmail())) {
      throw new RuntimeException("Email not found from OAuth2 provider");
    }

    Account account = accountRepository.findByEmail(oAuth2UserInfo.getEmail())
      .map(v -> updateExistingUser(v, oAuth2UserInfo))
      .orElseGet(() -> registerNewUser(userRequest, oAuth2UserInfo));

    return UserPrincipal.create(account, oAuth2User.getAttributes());
  }

  private Account registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    Account account = Account.builder()
      .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
      .username(oAuth2UserInfo.getEmail())
      .email(oAuth2UserInfo.getEmail())
      .build();
    return accountRepository.save(account);
  }

  private Account updateExistingUser(Account existingUser, OAuth2UserInfo oAuth2UserInfo) {
    return accountRepository.save(existingUser);
  }
}
