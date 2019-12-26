package com.jcarbad.sfgpetclinic.controllers;

import com.jcarbad.sfgpetclinic.model.Pet;
import com.jcarbad.sfgpetclinic.model.Visit;
import com.jcarbad.sfgpetclinic.services.PetService;
import com.jcarbad.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = this.petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/new")
    public String processNewVisitForm(@PathVariable Long ownerId, @Valid Visit visit, BindingResult result) {
        if (result.hasErrors())
            return "pets/createOrUpdateVisitForm";

        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }
}