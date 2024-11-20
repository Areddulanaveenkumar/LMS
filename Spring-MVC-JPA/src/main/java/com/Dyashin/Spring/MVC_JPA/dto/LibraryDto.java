package com.Dyashin.Spring.MVC_JPA.dto;

import com.Dyashin.Spring.MVC_JPA.entity.Section;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class LibraryDto {
    private int id;
    private String name;
    private String location;
    private LocalDate establish;
    private List<Section> sections;
}
