package com.luis.Prueba_bandesal.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "readers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reader implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "readers")
    private Set<Blog> blogs = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
