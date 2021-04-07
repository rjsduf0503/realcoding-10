package org.cnu.realcoding.service;

import com.mongodb.client.result.UpdateResult;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogAlreadyExistsException;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        if (dogRepository.findDogByNameAndOwnerNameAndOwnerPhoneNumber(dog.getName(), dog.getOwnerName(), dog.getOwnerPhoneNumber()) == null) {
            dogRepository.insertDog(dog);
        }
        else throw new DogAlreadyExistsException();
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
        List<Dog> dogs = dogRepository.findDogByName(name);
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

    //  Find dog by name, ownerName, ownerPhoneNumber
    //  Then update all Dog Args except MedicalRecords
    public UpdateResult UpdateAllArgs(String name, String ownerName, String ownerPhoneNumber, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber){
        this.getDogByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
        return dogRepository.updateAllDogArgsExceptMedicalRecords(name, ownerName, ownerPhoneNumber, newName, newKind, newOwnerName, newOwnerPhoneNumber);
    }


    //진료기록을 추가
    public UpdateResult addMedicalRecord(String name, String ownerName, String ownerPhoneNumber, List<String> medicalRecords){
        Dog dog = dogRepository.findDogByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
        if (dog == null) {
            throw new DogsNotFoundException();
        }
        return dogRepository.addingMedicalRecord(name,ownerName,ownerPhoneNumber,medicalRecords);
    }

}
