package com.jcarbad.sfgpetclinic.services;

import com.jcarbad.sfgpetclinic.model.Owner;
import com.jcarbad.sfgpetclinic.model.Person;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
