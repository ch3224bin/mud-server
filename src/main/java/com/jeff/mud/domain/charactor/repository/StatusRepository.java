package com.jeff.mud.domain.charactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.charactor.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
