package com.Dyashin.Spring.MVC_JPA.controller;

import com.Dyashin.Spring.MVC_JPA.dto.LibraryDto;
import com.Dyashin.Spring.MVC_JPA.dto.SectionDto;
import com.Dyashin.Spring.MVC_JPA.service.LibraryService;
import com.Dyashin.Spring.MVC_JPA.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SectionController {
    @Autowired
    LibraryService libraryService;
    @Autowired
    SectionService sectionService;

    /*@GetMapping("/add")
    public String homePage(){
        return "home";
    }*/

    @GetMapping("/add")
    public String addNewSection(Model model){
        SectionDto sectionDto=new SectionDto();
        model.addAttribute("section",sectionDto);
        return "AddDtoSection";
    }
    @PostMapping("/saveSection")
    public String addNewSection(SectionDto sectionDto){
        sectionService.addNewSection(sectionDto);
        return "redirect:/sectionMapping";
    }
    //Here we are having the view of the library page with section list
    /*@GetMapping("/addSection")
    public String addNewSection1(Model model){
        SectionDto sectionDto=new SectionDto();
        model.addAttribute("section",sectionDto);
        return "AddsectionDtoPage";
    }
    @PostMapping("/saveSections")
    public String addNewSection1(SectionDto sectionDto){
        sectionService.addNewSection(sectionDto);
        return "redirect:/view";
    }*/

    @GetMapping("/viewsection/{id}")
    public String viewSection(@PathVariable Integer id, Model model){
        SectionDto sectionDto= sectionService.viewLibrary(id);
        model.addAttribute("viewSection",sectionDto);
        return "viewsection";
    }
    @GetMapping("/deleteSection/{id}")
    public String deleteSection(@PathVariable Integer id){
        sectionService.deleteSection(id);
        return "redirect:/sectionMapping";
    }

    @GetMapping("/sectionUpdate/{id}")
    public String updateLibrary(@PathVariable(name = "id") Integer id,Model model){
        SectionDto sectionDto= sectionService.viewLibrary(id);
        model.addAttribute("updatesection",sectionDto);
        return "updateSection";
    }
    @GetMapping("/updateSection")
    public String updateLibrary(@ModelAttribute("update") SectionDto sectionDto){
        sectionService.updateLibrary(sectionDto);
        return "redirect:/sectionMapping";
    }
}
