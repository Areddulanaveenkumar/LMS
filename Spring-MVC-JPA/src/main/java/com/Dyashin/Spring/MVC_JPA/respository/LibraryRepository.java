package com.Dyashin.Spring.MVC_JPA.respository;

import com.Dyashin.Spring.MVC_JPA.entity.Library;
import com.Dyashin.Spring.MVC_JPA.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer> {
    List<Library> getLibraryByLocation(String location);

    List<Library> findByNameContainingOrLocationContaining(String name, String name1);

    Section getSectionById(Integer libId);
}
