package com.genielo.cafe.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genielo.cafe.backend.model.*;

public interface CommandRepository extends JpaRepository<Command, Long> {

	List<Command> findByServed(boolean served);
	List<Command> findByCooked(boolean cooked);

}
