package com.fundoonotesapp.labels.entity;

import com.fundoonotesapp.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fundoonotesapp.notes.entity.Note;

import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "labels",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name", "user_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonIgnore
    private User user;
    
    
    @ManyToMany(mappedBy = "labels")
    @JsonIgnore
    private Set<Note> notes = new HashSet<>();
}