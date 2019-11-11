package com.jcarbad.sfgpetclinic.repositories;

import com.jcarbad.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
