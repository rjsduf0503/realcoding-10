package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        dogRepository.insertDog(dog);
    }

    public List<Dog> getDogsByNameAndOwnerNameAndOwnerPhoneNumber(String name, String ownerName, String ownerPhoneNumber){
        List<Dog> dogs = dogRepository.findDogsByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
        if(dogs.isEmpty()) {
            throw new DogsNotFoundException();
        }
        return dogs;
    }

}
