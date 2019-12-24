package com.jcarbad.sfgpetclinic.formatters;

import com.jcarbad.sfgpetclinic.model.PetType;
import com.jcarbad.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Autowired
    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Optional<PetType> type = petTypeService.findAll().stream()
                .filter(petType -> petType.getName().equals(text))
                .findFirst();

        if (type.isPresent()) {
            return type.get();
        }

        throw new ParseException("Type " + text + "was not found", 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}