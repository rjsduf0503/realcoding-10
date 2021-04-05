package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//수업시간에 배운 적합한 HTTP METHOD를 선택할 것
//name 만을 파라미터로 받는 API 구현
//ownerName 만을 파라미터로 받는 API 구현
//ownerPhoneNumber 만을 파라미터로 받는 API 구현
//세 가지 파라미터를 모두 받아야만 하는 API 구현
//API는 DB에 저장된 정보를 반환해야 함
//존재하지 않을경우 XXX http status를 리턴
@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertDog(Dog dog){
        mongoTemplate.insert(dog);
    }

    //find 리턴값은 list임
    public List<Dog> findDogByName(String name) {
        List<Dog> dogs = mongoTemplate.find(
                Query.query(Criteria.where("name").is(name)),
                Dog.class
        );
        return dogs;
    }

    public List<Dog> findDogByOwnerName(String ownerName) {
        List<Dog> dogs = mongoTemplate.find(
                Query.query(Criteria.where("ownerName").is(ownerName)),
                Dog.class
        );
        return dogs;
    }
    public List<Dog> findDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        List<Dog> dogs = mongoTemplate.find(
                Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                Dog.class
        );
        return dogs;
    }


}