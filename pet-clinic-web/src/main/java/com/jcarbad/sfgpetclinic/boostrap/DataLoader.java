package com.jcarbad.sfgpetclinic.boostrap;

import com.jcarbad.sfgpetclinic.model.Owner;
import com.jcarbad.sfgpetclinic.model.Vet;
import com.jcarbad.sfgpetclinic.services.OwnerService;
import com.jcarbad.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner o1 = new Owner();
        o1.setFirstName("Armando");
        o1.setLastName("Carballo");

        Owner o2 = new Owner();
        o2.setFirstName("Rachel");
        o2.setLastName("Basulto");

        Vet v1 = new Vet();
        v1.setFirstName("L'Amiga");
        v1.setLastName("De Rache");

        Vet v2 = new Vet();
        v2.setFirstName("Doctor");
        v2.setLastName("Jimmy");

        System.out.println("Loading owners...");
        ownerService.save(o1);
        ownerService.save(o2);
        System.out.println("Done.");

        System.out.println("Loading vets...");
        vetService.save(v1);
        vetService.save(v2);
        System.out.println("Done.");
    }
}
