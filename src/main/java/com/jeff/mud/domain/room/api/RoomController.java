package com.jeff.mud.domain.room.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeff.mud.domain.room.RoomService;
import com.jeff.mud.domain.room.dto.RoomDc;

/**
 * 방 제작 API
 *
 * @author ChangHo Vin
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	private final RoomModelAssembler assembler;
	private final RoomService roomService;
	private final PagedResourcesAssembler<RoomDc> pagedAssembler;
	
	public RoomController(RoomModelAssembler assembler, RoomService roomService, PagedResourcesAssembler<RoomDc> pagedAssembler) {
		this.assembler = assembler;
		this.roomService = roomService;
		this.pagedAssembler = pagedAssembler;
	}

	@GetMapping
	public ResponseEntity<PagedModel<EntityModel<RoomDc>>> paging(Pageable pageable) {
		return ResponseEntity.ok(pagedAssembler.toModel(roomService.getPagedRooms(pageable), assembler));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<RoomDc>> one(@PathVariable("id") long id) {
		return ResponseEntity.ok(assembler.toModel(roomService.getRoom(id)));
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<RoomDc>> createRoom(@RequestBody RoomDc req) {
		EntityModel<RoomDc> res = assembler.toModel(roomService.createRoom(req));
		
		return ResponseEntity
	    .created(res.getRequiredLink(IanaLinkRelations.SELF).toUri())
	    .body(res);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateRoom(@PathVariable("id") long id, @RequestBody RoomDc req) {
		EntityModel<RoomDc> res = assembler.toModel(roomService.updateRoom(id, req));
		
		return ResponseEntity.noContent()
				.location(res.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.build();
	}
}
