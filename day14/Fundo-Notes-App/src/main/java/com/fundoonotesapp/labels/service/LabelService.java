package com.fundoonotesapp.labels.service;

import com.fundoonotesapp.labels.dto.CreateLabelRequest;

import com.fundoonotesapp.labels.dto.LabelResponse;
import com.fundoonotesapp.labels.entity.Label;
import com.fundoonotesapp.labels.repository.LabelRepository;
import com.fundoonotesapp.exception.common.ResourceNotFoundException;
import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final UserRepository userRepository;


    // ============================
    // CREATE LABEL
    // ============================

    public LabelResponse createLabel(
            CreateLabelRequest request,
            Principal principal
    ) {

        User user = getCurrentUser(principal);

        if (labelRepository.existsByNameAndUserId(
                request.name(),
                user.getId()
        )) {
            throw new IllegalArgumentException(
                    "Label already exists"
            );
        }

        Label label = new Label();
        label.setName(request.name());
        label.setUser(user);

        Label savedLabel = labelRepository.save(label);

        return new LabelResponse(
                savedLabel.getId(),
                savedLabel.getName()
        );
    }


    // ============================
    // GET ALL MY LABELS
    // ============================

    public List<LabelResponse> getMyLabels(
            Principal principal
    ) {

        User user = getCurrentUser(principal);

        return labelRepository.findByUserId(user.getId())
                .stream()
                .map(label -> new LabelResponse(
                        label.getId(),
                        label.getName()
                ))
                .toList();
    }


    // ============================
    // RENAME LABEL
    // ============================

    public LabelResponse renameLabel(
            Long labelId,
            CreateLabelRequest request,
            Principal principal
    ) {

        User user = getCurrentUser(principal);

        Label label = labelRepository
                .findByIdAndUserId(
                        labelId,
                        user.getId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Label not found"
                        )
                );

        // Prevent duplicate name
        if (labelRepository.existsByNameAndUserId(
                request.name(),
                user.getId()
        )) {
            throw new IllegalArgumentException(
                    "Label with this name already exists"
            );
        }

        label.setName(request.name());

        Label updatedLabel = labelRepository.save(label);

        return new LabelResponse(
                updatedLabel.getId(),
                updatedLabel.getName()
        );
    }


    // ============================
    // DELETE LABEL
    // ============================

    public void deleteLabel(
            Long labelId,
            Principal principal
    ) {

        User user = getCurrentUser(principal);

        Label label = labelRepository
                .findByIdAndUserId(
                        labelId,
                        user.getId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Label not found"
                        )
                );

        labelRepository.delete(label);
    }


    // ============================
    // CURRENT LOGGED-IN USER
    // ============================

    private User getCurrentUser(Principal principal) {

        return userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }
    
    
    
}