package com.Dyashin.Spring.MVC_JPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Library_Table")
public class Library {
    @Id
    @Column(name = "library_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "library_Name")
    private String name;
    @Column(name = "library_Location")
    private String location;
    @Column(name = "Establish")
    private LocalDate establish;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Library_Section",
            joinColumns = @JoinColumn(name = "library_Id"),
            inverseJoinColumns = @JoinColumn(name = "Section_Id")
    )
    private List<Section> sections;
}
