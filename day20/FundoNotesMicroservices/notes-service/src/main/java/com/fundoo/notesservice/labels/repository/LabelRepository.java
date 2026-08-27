package com.fundoo.notesservice.labels.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundoo.notesservice.labels.entity.*;

public interface LabelRepository
        extends JpaRepository<Label, Long> {

    List<Label> findByEmail(String email);

    Optional<Label> findByIdAndEmail(
            Long id,
            String email
    );

    boolean existsByNameAndEmail(
            String name,
            String email
    );

    boolean existsByNameAndEmailAndIdNot(
            String name,
            String email,
            Long id
    );
}