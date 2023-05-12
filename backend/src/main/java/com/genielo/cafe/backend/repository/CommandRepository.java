package com.genielo.cafe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genielo.cafe.backend.model.*;

public interface CommandRepository extends JpaRepository<Command, Long> {

	
}
