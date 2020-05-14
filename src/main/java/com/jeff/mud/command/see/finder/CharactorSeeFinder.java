package com.jeff.mud.command.see.finder;

import java.util.List;
import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.charactor.service.CharactorService;

@Component
@Order(4)
public class CharactorSeeFinder implements Finder<Seeable> {

	private final CharactorService charactorService;
	
	public CharactorSeeFinder(CharactorService charactorService) {
		this.charactorService = charactorService;
	}
	
	@Override
	public Optional<Seeable> find(CommandDataCarrier input) {
		List<CharactorDc> charactors = charactorService.getCharactorsInTheRoomWithOutMe(input);
		
		return charactors.stream()
			.filter(charactor -> charactor.getName().startsWith(input.getTarget()))
			.findFirst()
			.map(dc -> (Seeable) dc);
	}

}
