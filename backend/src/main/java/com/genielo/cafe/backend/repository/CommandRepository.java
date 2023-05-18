package com.genielo.cafe.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genielo.cafe.backend.model.*;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

	List<Command> findByServed(boolean served);
	List<Command> findByCooked(boolean cooked);
	List<Command> findByConfirmed(boolean confirmed);
	List<Command> findByCookedAndServed(boolean cooked, boolean served);

	
}
