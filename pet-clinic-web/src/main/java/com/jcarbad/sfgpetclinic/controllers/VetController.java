package com.jcarbad.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {

    @RequestMapping({"", "/", "/index", "/list"})
    public String listVets () {
        return "vets/index";
    }
}