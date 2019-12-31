package com.jcarbad.sfgpetclinic.controllers;

import com.jcarbad.sfgpetclinic.model.Vet;
import com.jcarbad.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets", "vets/", "/index", "/list", "/vets.html"})
    public String listVets (Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

    @ResponseBody
    @GetMapping("/api/vets")
    public Set<Vet> getJsonVets() {
        return vetService.findAll();
    }
}
