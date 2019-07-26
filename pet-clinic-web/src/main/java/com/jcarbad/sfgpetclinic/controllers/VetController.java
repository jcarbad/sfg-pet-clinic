package com.jcarbad.sfgpetclinic.controllers;

import com.jcarbad.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/", "/index", "/list"})
    public String listVets (Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
