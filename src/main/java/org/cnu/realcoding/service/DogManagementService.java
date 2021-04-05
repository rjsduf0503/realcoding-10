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

    public Dog getDogByNameAndOwnerNameAndOwnerPhoneNumber(String name, String ownerName, String ownerPhoneNumber){
        Dog dog = dogRepository.findDogByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
        if(dog == null) {
            throw new DogsNotFoundException();
        }
        return dog;
    }

}
