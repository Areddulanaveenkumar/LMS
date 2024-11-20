package com.Dyashin.Spring.MVC_JPA.service;

import com.Dyashin.Spring.MVC_JPA.dto.LibraryDto;
import com.Dyashin.Spring.MVC_JPA.dto.SectionDto;
import com.Dyashin.Spring.MVC_JPA.entity.Library;
import com.Dyashin.Spring.MVC_JPA.entity.Section;
/*import com.Dyashin.Spring.MVC_JPA.respository.LibrarySectionRepository;*/
import com.Dyashin.Spring.MVC_JPA.respository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;
   /* @Autowired
    LibrarySectionRepository librarySectionRepository;*/
    public List<SectionDto> getAllSection() {
       List<Section> sectionList= sectionRepository.findAll();
       List<SectionDto> sectionDtoList=new ArrayList<>();
       for(Section s:sectionList){
           SectionDto sectionDto=new SectionDto();
           sectionDto.setId(s.getId());
           sectionDto.setName(s.getName());
           sectionDtoList.add(sectionDto);
       }
       return sectionDtoList;
    }

    public Section addNewSection(SectionDto sectionDto) {
        Section section=new Section();
        section.setId(sectionDto.getId());
        section.setName(sectionDto.getName());
        /*System.out.println(section);*/
       return sectionRepository.save(section);
    }

    public SectionDto viewLibrary(Integer id) {
        Section optionalLibrary=sectionRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        SectionDto sectionDto=new SectionDto();
        sectionDto.setId(optionalLibrary.getId());
        sectionDto.setName(optionalLibrary.getName());
        return sectionDto;
    }

    public void deleteSection(Integer id) {
        sectionRepository.deleteById(id);
    }

    public Section updateLibrary(SectionDto sectionDto) {
        Section section=sectionRepository.findById(sectionDto.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
//        Library library1=library.get();
        section.setId(sectionDto.getId());
        section.setName(sectionDto.getName());
        return sectionRepository.save(section);
    }

    public List<SectionDto> getAllTheSections() {
        List<Section> section= sectionRepository.findAll();
        List<SectionDto> sectionDtos=new ArrayList<>();
        for(Section section1:section){
            SectionDto sectionDto=new SectionDto();
            sectionDto.setId(section1.getId());
            sectionDto.setName(section1.getName());
            sectionDtos.add(sectionDto);
        }
        return sectionDtos;
    }

    public void getSectionDetails(int secId,int libId) {
        /*sectionRepository.deleteById(secId);*/
        Section section=sectionRepository.findById(secId).orElseThrow(()->new RuntimeException("Section is not be found"));
        for(Library l: section.getLibraries()){
            if(l.getId()==libId) {
                l.getSections().remove(section);
            }
        }
        System.out.println(sectionRepository.save(section));
    }
}
