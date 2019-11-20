package com.jcarbad.sfgpetclinic.services.datajpa;

import com.jcarbad.sfgpetclinic.model.Owner;
import com.jcarbad.sfgpetclinic.repositories.OwnerRepository;
import com.jcarbad.sfgpetclinic.repositories.PetRepository;
import com.jcarbad.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    private final Long ownerId = 1L;
    private final String lastName = "Smith";
    private Owner retrieved;

    @BeforeEach
    void setUp() {
        retrieved = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findByLastName() {
        Owner smith = Owner.builder().id(ownerId).lastName(lastName).build();

        when(ownerRepository.findByLastName(lastName)).thenReturn(smith);

        retrieved = ownerJpaService.findByLastName(lastName);

        assertEquals(retrieved.getId(), ownerId);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set owners = ownerJpaService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(retrieved));

        Owner owner = ownerJpaService.findById(ownerId);

        assertEquals(owner.getId(), ownerId);
    }

    @Test
    void save() {
        Owner toSave = Owner.builder().id(ownerId).build();

        when(ownerRepository.save(any())).thenReturn(retrieved);

        Owner saved = ownerJpaService.save(toSave);

        assertNotNull(saved);
    }

    @Test
    void delete() {
        ownerJpaService.delete(retrieved);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(ownerId);

        verify(ownerRepository).deleteById(anyLong());
    }
}