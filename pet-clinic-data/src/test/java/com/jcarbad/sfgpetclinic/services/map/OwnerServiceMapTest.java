package com.jcarbad.sfgpetclinic.services.map;

import com.jcarbad.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;

    private Long ownerId = 1L;
    private String lastName = "Eizenberger";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    // If we had fancier validations for save:
    // saveExistingId
    // saveWithNoId
    // ...
    void save() {
        Owner owner2 = Owner.builder().id(2L).build();
        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(savedOwner.getId(), 2L);
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner retrieved = ownerServiceMap.findByLastName(lastName);

        assertNotNull(retrieved);
        assertEquals(retrieved.getId(), ownerId);
    }

    @Test
    void findByLastNameNotFound() {
        Owner retrieved = ownerServiceMap.findByLastName("McSomething");

        assertNull(retrieved);
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);

        assertEquals(ownerServiceMap.findAll().size(), 0);
    }

    @Test
    void delete() {
        Owner toDelete = ownerServiceMap.findById(ownerId);
        ownerServiceMap.delete(toDelete);

        assertEquals(ownerServiceMap.findAll().size(), 0);
    }
}