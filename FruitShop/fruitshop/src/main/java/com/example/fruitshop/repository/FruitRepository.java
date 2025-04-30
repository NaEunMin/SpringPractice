package com.example.fruitshop.repository;


import com.example.fruitshop.model.Fruit;

import java.util.List;

//CRUD (create, read, update, delete)
public interface FruitRepository {

    //create -> 과일정보를 저장한다.
    Fruit saveFruit(Fruit fruit);

    //read -> id를 통해 특정 과일을 검색한다.
    Fruit findFruitById(Long id);

    //read -> 모든 과일목록을 검색한다.
    List<Fruit> findAllFruits();

    //update -> 과일의 정보를 갱신한다.
    void updateFruit(Long id, Fruit updateFruit);

    //delete -> id를 통해 특정 과일의 정보를 삭제한다.
    void deleteFruit(Long id);

    //delete -> 전체 과일정보를 삭제한다.
    void deleteAllFruits();
}
