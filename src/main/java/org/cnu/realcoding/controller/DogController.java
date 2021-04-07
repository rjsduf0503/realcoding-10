package org.cnu.realcoding.controller;

import com.mongodb.client.result.UpdateResult;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController 
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){ 
        dogManagementService.insertDog(dog);
    }

    //name 만을 파라미터로 받는 API 구현
    @GetMapping("/dogs/name/{name}")
    public  List<Dog> getDogByName(@PathVariable String name) {
        return dogManagementService.getDogByName(name);
    }

    //ownerName 만을 파라미터로 받는 API 구현
    @GetMapping("/dogs/ownerName/{ownerName}")
    public  List<Dog> getDogByOwnerName(@PathVariable String ownerName) {
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    //ownerPhoneNumber 만을 파라미터로 받는 API 구현
    @GetMapping("/dogs/ownerPhoneNumber/{ownerPhoneNumber}")
    public  List<Dog> getDogByOwnerPhoneNumber(@PathVariable String ownerPhoneNumber) {
        return dogManagementService.getDogByOwnerPhoneNumber(ownerPhoneNumber);
    }

    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByNameAndOwnerNameAndOwnerPhoneNumber
            (@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
    }

    //  전체 정보 통째로 수정 API
    //  Method : Put, Message : PathVariable, Resource : dogs
    @PutMapping("/dogs/{name}/{kind}/{ownerName}/{ownerPhoneNumber}")
    public UpdateResult UpdateAllArgs(@PathVariable String name, @PathVariable  String ownerName, @PathVariable  String ownerPhoneNumber, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber){
        return dogManagementService.UpdateAllArgs(name, ownerName, ownerPhoneNumber, newName, newKind, newOwnerName, newOwnerPhoneNumber);
    }



    @PutMapping("dogs/medicalRecords/{name}/{ownerName}/{ownerPhoneNumber}/{medicalRecords}")
    public void addMedicalRecord(@PathVariable String name, @PathVariable  String ownerName, @PathVariable  String ownerPhoneNumber, @PathVariable List<String> medicalRecords) {
        dogManagementService.addMedicalRecord(name, ownerName, ownerPhoneNumber, medicalRecords);
    }

}

