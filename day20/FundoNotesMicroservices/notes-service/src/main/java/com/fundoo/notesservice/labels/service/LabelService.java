package com.fundoo.notesservice.labels.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fundoo.notesservice.labels.dto.CreateLabelRequest;
import com.fundoo.notesservice.labels.dto.LabelResponse;
import com.fundoo.notesservice.labels.dto.UpdateLabelRequest;
import com.fundoo.notesservice.labels.entity.Label;
import com.fundoo.notesservice.labels.repository.LabelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelResponse createLabel(
            String email,
            CreateLabelRequest request
    ) {

        if (labelRepository.existsByNameAndEmail(
                request.name(),
                email
        )) {
            throw new RuntimeException(
                    "A label named '" + request.name() + "' already exists."
            );
        }

        Label label = Label.builder()
                .name(request.name())
                .email(email)
                .build();

        return mapToResponse(
                labelRepository.save(label)
        );
    }

    public List<LabelResponse> getMyLabels(
            String email
    ) {

        return labelRepository.findByEmail(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public LabelResponse updateLabel(
            Long labelId,
            String email,
            UpdateLabelRequest request
    ) {

        Label label = getLabelByIdAndEmail(
                labelId,
                email
        );

        if (labelRepository.existsByNameAndEmailAndIdNot(
                request.name(),
                email,
                labelId
        )) {
            throw new RuntimeException(
                    "A label named '" + request.name() + "' already exists."
            );
        }

        label.setName(request.name());

        return mapToResponse(
                labelRepository.save(label)
        );
    }

    public void deleteLabel(
            Long labelId,
            String email
    ) {

        Label label = getLabelByIdAndEmail(
                labelId,
                email
        );

        labelRepository.delete(label);
    }

    private Label getLabelByIdAndEmail(
            Long labelId,
            String email
    ) {

        return labelRepository
                .findByIdAndEmail(labelId, email)
                .orElseThrow(() ->
                        new RuntimeException("Label not found")
                );
    }

    private LabelResponse mapToResponse(
            Label label
    ) {

        return new LabelResponse(
                label.getId(),
                label.getName()
        );
    }
}