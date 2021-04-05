package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.cnu.realcoding.exception.DogsNotFoundException;
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

    //list가 빈 list인 경우 404뜨게 한다.
    public List<Dog> getDogByName(String name) {
        List<Dog> dogs =  dogRepository.findDogByName(name);
        if(dogs.isEmpty()) {
            throw new DogsNotFoundException();
        }
        return dogs;

    }
    public List<Dog> getDogByOwnerName(String ownerName) {
        List<Dog> dogs  =  dogRepository.findDogByOwnerName(ownerName);
        if(dogs.isEmpty()) {
            throw new DogsNotFoundException();
        }
        return dogs;

    }
    public  List<Dog> getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        List<Dog> dogs  =  dogRepository.findDogByOwnerPhoneNumber(ownerPhoneNumber);
        if(dogs.isEmpty()) {
            throw new DogsNotFoundException();
        }
        return dogs;
    }
    public List<Dog> getDogsByNameAndOwnerNameAndOwnerPhoneNumber(String name, String ownerName, String ownerPhoneNumber){
        List<Dog> dogs = dogRepository.findDogsByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);

        if(dogs.isEmpty()) {
            throw new DogsNotFoundException();
        }
        return dogs;
    }

}
