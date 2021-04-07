package org.cnu.realcoding.repository;

import com.mongodb.client.result.UpdateResult;
import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    public Dog findDogByNameAndOwnerNameAndOwnerPhoneNumber(String name, String ownerName, String ownerPhoneNumber){
        Dog dog = mongoTemplate.findOne(
                Query.query(
                        Criteria.where("name").is(name).and
                                ("ownerName").is(ownerName).and
                                ("ownerPhoneNumber").is(ownerPhoneNumber)
                ), Dog.class
        );
        return dog;
    }

    //  통째로 강아지 정보를 덮어쓸 수 있는 API 구현
    //  name, ownerName, ownerPhoneNumber 이 모두 같으면 통째로 업데이트 해준다.
    public UpdateResult updateAllDogArgsExceptMedicalRecords(String name, String ownerName, String ownerPhoneNumber, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).and
                ("ownerName").is(ownerName).and
                ("ownerPhoneNumber").is(ownerPhoneNumber)
        );
        Update update = new Update();
        update.set("name", newName);
        update.set("kind", newKind);
        update.set("ownerName", newOwnerName);
        update.set("ownerPhoneNumber", newOwnerPhoneNumber);

        return mongoTemplate.updateFirst(query, update, Dog.class);
    }

    //kind 정보만 수정하는 API
    // name, ownerName, ownerPhoneNumber 정보를 이용해 dog을 찾고  kind정보를 업데이트 해준다.
    public UpdateResult UpdateToKind(String name, String ownerName, String ownerPhoneNumber, String kind) {
        return mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(name).and
                                ("ownerName").is(ownerName).and
                                ("ownerPhoneNumber").is(ownerPhoneNumber)
                ),
                Update.update("kind",kind),
                Dog.class
        );
    }


    public UpdateResult addingMedicalRecord(String name, String ownerName, String ownerPhoneNumber,String medicalRecords){
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query().addCriteria(criteria);
        Update update = new Update().addToSet("medicalRecords",medicalRecords);
        return mongoTemplate.updateFirst(query,update,Dog.class);

    }

}