package com.fundoonotesapp.labels.repository;

import com.fundoonotesapp.labels.entity.Label;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

	// Get all labels belonging to one user
	List<Label> findByUserId(Long userId);

	// Find a label only if it belongs to the user
	Optional<Label> findByIdAndUserId(Long labelId, Long userId);

	// Check duplicate label name for the same user
	boolean existsByNameAndUserId(String name, Long userId);
}