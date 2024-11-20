package com.Dyashin.Spring.MVC_JPA.controller;

import com.Dyashin.Spring.MVC_JPA.dto.LibraryDto;
import com.Dyashin.Spring.MVC_JPA.dto.LibrarySectionDto;
import com.Dyashin.Spring.MVC_JPA.dto.SectionDto;
import com.Dyashin.Spring.MVC_JPA.entity.Library;
import com.Dyashin.Spring.MVC_JPA.entity.Section;
import com.Dyashin.Spring.MVC_JPA.service.LibraryService;
import com.Dyashin.Spring.MVC_JPA.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    LibraryService libraryService;
    @Autowired
    SectionService sectionService;

    @GetMapping("/")
    public String homePage(Model model){
        List<LibraryDto> list=libraryService.getAllLibrary();
        model.addAttribute("AllLibrary",list);
        return "home";
    }
    @GetMapping("/addNewLibrary")
    public String addNewLibrary(Model model){
        LibraryDto libraryDto=new LibraryDto();
        model.addAttribute("library",libraryDto);
        return "addLibrary";
    }
    @PostMapping("/save")
    public String addNewLibrary(LibraryDto libraryDto){
        libraryService.getAddLibrary(libraryDto);
        return "redirect:/";
    }
    @PostMapping("/location")
    public String filterBy(Model model,@RequestParam String location){
        List<LibraryDto> list=libraryService.getLibraryByLocation(location);
        model.addAttribute("AllLibrary1",list);
        /*model.addAttribute("AllLocations",libraryService.getAllLibraryLocation());*/
        return "home";
    }
    @GetMapping("/view/{id}")
    public String viewLibrary(@PathVariable Integer id, Model model){
        LibraryDto libraryDto= libraryService.viewLibrary(id);
        model.addAttribute("view",libraryDto);
         return "view";
    }
    @GetMapping("/libraryUpdate/{id}")
    public String updateLibrary(@PathVariable(name = "id") Integer id,Model model){
       LibraryDto libraryDto= libraryService.viewLibrary(id);
       model.addAttribute("update",libraryDto);
       return "updateLibrary";
    }
    @GetMapping("/updateLibrary")
    public String updateLibrary(@ModelAttribute("update") LibraryDto libraryDto){
        libraryService.updateLibrary(libraryDto);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteLibrary(@PathVariable Integer id){
        libraryService.deleteLibrary(id);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String searchEngineOfLibrary(@RequestParam("search") String name,Model model){
       List<LibraryDto> list= libraryService.searchEngineOfLibrary(name);
       model.addAttribute("AllLibrary",list);
       return "home";
    }
    @GetMapping("/sectionMapping")
    public String section(Model model){
       List<SectionDto> sectionDtos= sectionService.getAllSection();
       model.addAttribute("section",sectionDtos);
       return "section";
    }
    //Here we are going to work with th view library part

    @GetMapping("/specificLibrary/{id}")
    public String addSectionToSpecificLibrary(@PathVariable("id") int id,Model model){
        List<SectionDto> sectionList=sectionService.getAllTheSections();
        LibrarySectionDto librarySectionDto=new LibrarySectionDto();
        librarySectionDto.setLibId(id);
        model.addAttribute("Sections",sectionList);
        model.addAttribute("dto",librarySectionDto);
        return "AddsectionDtoPage";
    }
    @PostMapping("/saveDtoSection")
    public String addSectionToLib(LibrarySectionDto librarySectionDto){
        Integer libra =libraryService.addSectionToLib(librarySectionDto);
        System.out.println(libra);
        return "redirect:/view/"+libra;
    }
    @GetMapping("/updateSection/{id}")
    public String updateLibrarySection(@PathVariable("id")int id,Model model){
        List<SectionDto> sectionDtoList=sectionService.getAllTheSections();
        LibrarySectionDto librarySectionDto=new LibrarySectionDto();
        model.addAttribute("librarySection",librarySectionDto);
        return "updateSectionLibrary";
    }

//    public String addUpdateSection(LibrarySectionDto librarySectionDto){
//       Integer li= libraryService.addUpdateSection(librarySectionDto);
//       return "redirect:/view/"+li;
//    }
    @GetMapping("/sectionDelete")
    public String deleteSectionDetails(@PathVariable int secId, @PathVariable int libId){
        sectionService.getSectionDetails(secId,libId);
        return "redirect:/view/" + libId;
    }
    

}
