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

        PetType savedDogPetType = petTypeService.save(PetType.builder().name("Dog").build());

        PetType savedCatPetType = petTypeService.save(PetType.builder().name("Cat").build());

        Specialty savedRadiology = specialtyService.save(Specialty.builder().description("Radiology").build());

        Specialty savedSurgery = specialtyService.save(Specialty.builder().description("Surgery").build());

        Specialty savedDentistry = specialtyService.save(Specialty.builder().description("Dentistry").build());

        Owner o1 = Owner.builder()
                .firstName("Armando")
                .lastName("Carballo")
                .pets(new HashSet<>())
                .build();

        Owner o2 = Owner.builder()
                .firstName("Rachel")
                .lastName("Basulto")
                .pets(new HashSet<>())
                .build();

        System.out.println("Loading owners...");
        ownerService.save(o1);
        ownerService.save(o2);
        System.out.println("Done.");

        Pet coqui = Pet.builder()
                .name("Coqui")
                .owner(o2)
                .birthDate(LocalDate.now())
                .petType(savedDogPetType)
                .build();

        o1.getPets().add(coqui);

        Pet kiara = Pet.builder()
                .name("Kiara")
                .owner(o2)
                .birthDate(LocalDate.now())
                .petType(savedCatPetType)
                .build();

        System.out.println("Loading Pets...");
        petService.save(coqui);
        petService.save(kiara);
        System.out.println("Done.");

        o2.getPets().add(kiara);
        ownerService.save(o1);
        ownerService.save(o2);

        Vet v1 = Vet.builder()
                .firstName("L'Amiga")
                .lastName("De Rache")
                .specialties(new HashSet<>(Arrays.asList(savedRadiology, savedSurgery)))
                .build();

        Vet v2 = Vet.builder()
                .firstName("Doctor")
                .lastName("Jimmy")
                .specialties(new HashSet<>(Arrays.asList(savedRadiology, savedDentistry)))
                .build();

        Visit coquisVisit = Visit.builder()
                .pet(coqui)
                .date(LocalDate.now())
                .description("La visita de Coqui")
                .build();
        visitService.save(coquisVisit);

        Visit kiarasVisit = Visit.builder()
                .pet(kiara)
                .date(LocalDate.now())
                .description("La visita de Kiara")
                .build();
        visitService.save(kiarasVisit);


        System.out.println("Loading vets...");
        vetService.save(v1);
        vetService.save(v2);
        System.out.println("Done.");
    }
}
