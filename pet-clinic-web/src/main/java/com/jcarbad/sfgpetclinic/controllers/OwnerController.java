package com.jcarbad.sfgpetclinic.controllers;

import com.jcarbad.sfgpetclinic.model.Owner;
import com.jcarbad.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({"", "/", "index"})
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (StringUtils.isEmpty(owner.getLastName())) {
            owner.setLastName("");
        }

        List<Owner> results = this.ownerService.findAllByLastNameLike(String.format("%%%s%%", owner.getLastName()));
        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }

        if (results.size() == 1) {
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }

        model.addAttribute("selections", results);
        return "owners/ownersList";
    }

    @GetMapping("/{id}")
    public ModelAndView displayOwner(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findById(id));
        return mav;
    }
}
