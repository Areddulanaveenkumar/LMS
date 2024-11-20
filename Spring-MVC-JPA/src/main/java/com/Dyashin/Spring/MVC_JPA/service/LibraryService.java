package com.Dyashin.Spring.MVC_JPA.service;

import com.Dyashin.Spring.MVC_JPA.dto.LibraryDto;
import com.Dyashin.Spring.MVC_JPA.dto.LibrarySectionDto;
import com.Dyashin.Spring.MVC_JPA.entity.Library;
import com.Dyashin.Spring.MVC_JPA.entity.Section;
import com.Dyashin.Spring.MVC_JPA.respository.LibraryRepository;
import com.Dyashin.Spring.MVC_JPA.respository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    SectionRepository sectionRepository;
    public Library getAddLibrary(LibraryDto libraryDto) {
        Library library=new Library();
        library.setName(libraryDto.getName());
        library.setLocation(libraryDto.getLocation());
        library.setEstablish(libraryDto.getEstablish());
        return libraryRepository.save(library);
    }

    public List<LibraryDto> getAllLibrary() {
       List<Library> libraries= libraryRepository.findAll();
       List<LibraryDto> list=new ArrayList<>();
       for(Library l:libraries){
           LibraryDto libraryDto=new LibraryDto();
           libraryDto.setId(l.getId());
           libraryDto.setName(l.getName());
           libraryDto.setLocation(l.getLocation());
           libraryDto.setEstablish(l.getEstablish());
           list.add(libraryDto);
       }
       return list;
    }

    /*public List<LibraryDto> getAllLibraryLocation() {
       List<Library> list= libraryRepository.findAll();
        List<LibraryDto> unique=list.stream().map(library ->{
            LibraryDto libraryDto=new LibraryDto();
            libraryDto.setLocation(library.getLocation());
            return libraryDto;
        })
                .distinct()
                .collect(Collectors.toList());
        return unique;
    }*/

    public List<LibraryDto> getLibraryByLocation(String location) {
       List<Library> libraries= libraryRepository.getLibraryByLocation(location);
        List<LibraryDto> list=new ArrayList<>();
        for(Library l:libraries){
            LibraryDto libraryDto=new LibraryDto();
            libraryDto.setId(l.getId());
            libraryDto.setName(l.getName());
            libraryDto.setLocation(l.getLocation());
            libraryDto.setEstablish(l.getEstablish());
            list.add(libraryDto);
        }
        return list;
    }

    public LibraryDto viewLibrary(Integer id) {
        Library optionalLibrary=libraryRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
//        LibraryDto list=new LibraryDto();
/*
        Library library=optionalLibrary.get();
*/
        LibraryDto libraryDto=new LibraryDto();
        libraryDto.setId(optionalLibrary.getId());
        libraryDto.setName(optionalLibrary.getName());
        libraryDto.setLocation(optionalLibrary.getLocation());
        libraryDto.setEstablish(optionalLibrary.getEstablish());
        libraryDto.setSections(optionalLibrary.getSections());
        return libraryDto;
    }

    public Library updateLibrary(LibraryDto libraryDto) {
        Library library1=libraryRepository.findById(libraryDto.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
//        Library library1=library.get();
        library1.setId(libraryDto.getId());
        library1.setName(libraryDto.getName());
        library1.setLocation(libraryDto.getLocation());
        library1.setEstablish(libraryDto.getEstablish());
        return libraryRepository.save(library1);
    }

    public String deleteLibrary(Integer id) {
        libraryRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public List<LibraryDto> searchEngineOfLibrary(String name) {
       List<Library> libraryList= libraryRepository.findByNameContainingOrLocationContaining(name,name);
       List<LibraryDto> list=new ArrayList<>();
       for(Library l:libraryList){
           LibraryDto libraryDto=new LibraryDto();
           libraryDto.setId(l.getId());
           libraryDto.setName(l.getName());
           libraryDto.setLocation(l.getLocation());
           libraryDto.setEstablish(l.getEstablish());
           list.add(libraryDto);
       }
       return list;
    }

    public Integer addSectionToLib(LibrarySectionDto librarySectionDto) {
       Library library= libraryRepository.findById(librarySectionDto.getLibId()).orElseThrow(()->new RuntimeException("Library not Found"));
        /*System.out.println(librarySectionDto.getSecId());*/
      Section section= sectionRepository.findById(librarySectionDto.getSecId()).orElseThrow(()->new RuntimeException("Section not found"));
      section.getLibraries().add(library);
      library.getSections().add(section);
      libraryRepository.save(library);
      sectionRepository.save(section);
        System.out.println(library.getId());
      return library.getId();
    }

    public Section getSectionDetails(Integer libId) {
       return libraryRepository.getSectionById(libId);
    }

    public LibraryDto getTheMatchingLibraryById(int id) {
        Optional<Library> libraries=libraryRepository.findById(id);
        Library libraries1=libraries.get();
        LibraryDto librariesDto=new LibraryDto();
        librariesDto.setId(libraries1.getId());
        librariesDto.setName(libraries1.getName());
        librariesDto.setLocation(libraries1.getLocation());
        librariesDto.setEstablish(libraries1.getEstablish());
        return librariesDto;
    }

//    public Integer addUpdateSection(LibrarySectionDto librarySectionDto) {
//         Library library=libraryRepository.findById(librarySectionDto.getLibId()).orElseThrow(()->new RuntimeException("library section"));
//        Section section=sectionRepository.findById(librarySectionDto.getSecId()).orElseThrow(()->new RuntimeException("section library"));
////        section.setLibraries(library);
//        List<Section> sectionList = library.getSections();
//
//
//    }
}
