package com.qa.census.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.census.entity.Saiyan;

public interface SaiyanRepo extends JpaRepository<Saiyan, Long> {

	
}
