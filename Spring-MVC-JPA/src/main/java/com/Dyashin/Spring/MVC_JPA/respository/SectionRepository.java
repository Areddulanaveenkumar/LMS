package com.Dyashin.Spring.MVC_JPA.respository;

import com.Dyashin.Spring.MVC_JPA.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Integer> {
}
