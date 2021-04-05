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
    @ResponseStatus(HttpStatus.CREATED) //  201번
    public void createDogs(@RequestBody Dog dog) {
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByNameAndOwnerNameAndOwnerPhoneNumber
            (@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByNameAndOwnerNameAndOwnerPhoneNumber(name, ownerName, ownerPhoneNumber);
    }
}
