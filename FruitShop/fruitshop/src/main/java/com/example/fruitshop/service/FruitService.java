package com.example.fruitshop.service;

import com.example.fruitshop.model.Fruit;

import java.util.List;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    Fruit getFruitById(Long id);
    List<Fruit> getAllFruits();
    void updateFruit(Long id, Fruit updateFruit);
    void deleteFruit(Long id);
    void deleteAllFruits();
}
