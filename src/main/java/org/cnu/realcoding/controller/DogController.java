package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Dog> getDogsByNameAndOwnerNameAndOwnerPhoneNumber
            (@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogsByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
    }
}

