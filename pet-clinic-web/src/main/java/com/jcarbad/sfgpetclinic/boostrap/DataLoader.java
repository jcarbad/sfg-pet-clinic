package com.jcarbad.sfgpetclinic.boostrap;

import com.jcarbad.sfgpetclinic.model.*;
import com.jcarbad.sfgpetclinic.services.*;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final VisitService visitService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, VisitService visitService, PetService petService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.visitService = visitService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner o1 = new Owner();
        o1.setFirstName("Armando");
        o1.setLastName("Carballo");

        Owner o2 = new Owner();
        o2.setFirstName("Rachel");
        o2.setLastName("Basulto");

        System.out.println("Loading owners...");
        ownerService.save(o1);
        ownerService.save(o2);
        System.out.println("Done.");

        Pet coqui = new Pet();
        coqui.setName("Coqui");
        coqui.setOwner(o2);
        coqui.setBirthDate(LocalDate.now());
        coqui.setPetType(savedDogPetType);
        o1.getPets().add(coqui);

        Pet kiara = new Pet();
        kiara.setName("Kiara");
        kiara.setOwner(o2);
        kiara.setBirthDate(LocalDate.now());
        kiara.setPetType(savedCatPetType);
        o2.getPets().add(kiara);

        Vet v1 = new Vet();
        v1.setFirstName("L'Amiga");
        v1.setLastName("De Rache");
        v1.setSpecialties(new HashSet<>(Arrays.asList(savedRadiology, savedSurgery)));

        Vet v2 = new Vet();
        v2.setFirstName("Doctor");
        v2.setLastName("Jimmy");
        v1.setSpecialties(new HashSet<>(Arrays.asList(savedRadiology, savedDentistry)));

        Visit coquisVisit = new Visit();
        coquisVisit.setPet(coqui);
        coquisVisit.setDate(LocalDate.now());
        coquisVisit.setDescription("La visita de Coqui");

        Visit kiarasVisit = new Visit();
        kiarasVisit.setPet(kiara);
        kiarasVisit.setDate(LocalDate.now());
        kiarasVisit.setDescription("La visita de Kiara");



        System.out.println("Loading vets...");
        vetService.save(v1);
        vetService.save(v2);
        System.out.println("Done.");
    }
}
