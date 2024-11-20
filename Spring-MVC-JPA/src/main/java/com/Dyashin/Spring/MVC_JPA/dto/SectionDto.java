package com.Dyashin.Spring.MVC_JPA.dto;

import com.Dyashin.Spring.MVC_JPA.entity.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    private  int id;
    private  String name;
    private List<Library> libraries;
}
