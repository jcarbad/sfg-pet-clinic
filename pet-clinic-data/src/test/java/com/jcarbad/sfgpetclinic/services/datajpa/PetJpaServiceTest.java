package com.jcarbad.sfgpetclinic.services.datajpa;

import com.jcarbad.sfgpetclinic.model.Pet;
import com.jcarbad.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetJpaServiceTest {

    @Mock
    private static PetRepository petRepository;

    @InjectMocks
    private PetJpaService petJpaService;

    private Pet petOne = Pet.builder().id(1L).build();
    private Pet petTwo = Pet.builder().id(2L).build();
    private Pet petThree = Pet.builder().id(3L).build();

    private Set<Pet> petSet = new HashSet<>(Arrays.asList(petOne, petTwo));

    @Test
    void findAll() {
        when(petRepository.findAll()).thenReturn(petSet);
        Set pets = petJpaService.findAll();

        assertEquals(pets, petSet);
    }

    @Test
    void findById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(petOne));
        Pet retrieved = petJpaService.findById(1L);

        assertEquals(retrieved, petOne);
    }

    @Test
    void save() {
        when(petRepository.save(petThree)).thenReturn(petThree);
        Pet saved = petJpaService.save(petThree);

        assertEquals(saved, petThree);
    }

    @Test
    void delete() {
        petJpaService.delete(petThree);
        verify(petRepository).delete(petThree);
    }
}