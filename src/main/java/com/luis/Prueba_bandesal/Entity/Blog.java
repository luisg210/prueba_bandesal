package com.luis.Prueba_bandesal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blogs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Titulo no puede ser vacio")
    private String title;

    @NotEmpty(message = "Descripcion no puede ser vacio")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "blogs_readers",
            joinColumns = @JoinColumn(name = "b_id"),
            inverseJoinColumns = @JoinColumn(name = "r_id")
    )
    @JsonIgnore
    private Set<Reader> readers = new HashSet<>();

}
