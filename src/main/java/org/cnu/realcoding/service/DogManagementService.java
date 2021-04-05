package org.cnu.realcoding.service;

import com.mongodb.client.result.UpdateResult;
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

    public Dog getDogByNameAndOwnerNameAndOwnerPhoneNumber(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
        if (dog == null) {
            throw new DogsNotFoundException();
        }
        return dog;
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

    //kind 정보만 수정하는 API
    public UpdateResult UpdateToKind(String name, String ownerName, String ownerPhoneNumber, String kind) {
        this.getDogsByNameAndOwnerNameAndOwnerPhoneNumber(name,ownerName,ownerPhoneNumber);
        return dogRepository.updateDogsFind(name, ownerName,ownerPhoneNumber,kind);
    }

}
