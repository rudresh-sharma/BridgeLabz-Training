package com.fundoo.notesservice.note.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.fundoo.notesservice.labels.entity.Label;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

	public enum NoteStatus {
		ACTIVE, PINNED, ARCHIVED, TRASHED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private NoteStatus status = NoteStatus.ACTIVE;

	@Column(name = "user_email", nullable = false)
	private String email;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@ManyToMany
	@JoinTable(name = "note_labels", joinColumns = @JoinColumn(name = "note_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
	@Builder.Default
	private Set<Label> labels = new HashSet<>();

	@PrePersist
	public void onCreate() {
		LocalDateTime now = LocalDateTime.now();

		this.createdAt = now;
		this.updatedAt = now;
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}