package com.jeff.mud.domain.charactor.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.global.account.dao.AccountRepository;
import com.jeff.mud.global.account.domain.Account;

@ActiveProfiles("test")
@SpringBootTest
public class PlayerRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	public void testFindByAccount() {
		Account account = accountRepository.findById(1L).get();
		
		Player player = playerRepository.findByAccount(account).get();
		
		Assertions.assertEquals("액션가면", player.getName());
	}
}
