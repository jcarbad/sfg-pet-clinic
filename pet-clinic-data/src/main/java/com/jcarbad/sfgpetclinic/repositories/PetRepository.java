package com.jcarbad.sfgpetclinic.repositories;

import com.jcarbad.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
