package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



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

    public List<Dog> findDogsByNameAndOwnerNameAndOwnerPhoneNumber(String name, String ownerName, String ownerPhoneNumber){
        List<Dog> dogs = mongoTemplate.find(
                Query.query(
                        Criteria.where("name").is(name).and
                                ("ownerName").is(ownerName).and
                                ("ownerPhoneNumber").is(ownerPhoneNumber)
                ), Dog.class
        );
        return dogs;
    }

}
