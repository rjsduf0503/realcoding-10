package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller 위에는 어노테이션 사용(객체의 생성과 소멸과 관련)
@RestController //JSON만 주고받는 서버기때문에 RestController 사용
public class DogController {
    //controller는 외부로부터 request를 받아 저장?만

    @Autowired //?
    private DogManagementService dogManagementService;

    @PostMapping("/dogs") //post 방식의 API, url뒤에 올 정보?
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){ //JSON을 Dog로 만들어줘
        dogManagementService.insertDog(dog);
    }


    //RequestParam을 넣음으로 localhost:8080/dogs?name=ian
    //PathVariable을 넣으면 localhost:8080/dogs/ian
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

}