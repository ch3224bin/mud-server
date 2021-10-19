package com.jeff.mud.domain.room.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.jeff.mud.domain.room.dto.RoomDc;

@Component
public class RoomModelAssembler implements RepresentationModelAssembler<RoomDc, EntityModel<RoomDc>> {

	private final PagedResourcesAssembler<RoomDc> pagedResourcesAssembler;

	public RoomModelAssembler(PagedResourcesAssembler<RoomDc> pagedResourcesAssembler) {
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@Override
	public EntityModel<RoomDc> toModel(RoomDc room) {
		return EntityModel.of(room,
			      linkTo(methodOn(RoomController.class).one(room.getId())).withSelfRel(),
						linkTo(methodOn(RoomController.class).updateRoom(room.getId(), room)).withRel("update")
		);
	}

	public PagedModel<EntityModel<RoomDc>> toPagedModel(Page<RoomDc> pagedRooms) {
		return pagedResourcesAssembler.toModel(pagedRooms, this);
	}
}
