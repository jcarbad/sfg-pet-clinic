package com.jcarbad.sfgpetclinic.services;

import com.jcarbad.sfgpetclinic.model.Owner;
import com.jcarbad.sfgpetclinic.model.Person;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
