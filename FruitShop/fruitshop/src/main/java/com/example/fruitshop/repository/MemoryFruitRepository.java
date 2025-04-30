package com.example.fruitshop.repository;

import com.example.fruitshop.model.Fruit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryFruitRepository implements FruitRepository {
    private final Map<Long, Fruit> fruits = new HashMap<>();
    private Long fruitID = 0L;

    @Override
    public Fruit saveFruit(Fruit fruit) {
        fruit.setId(++fruitID);
        fruits.put(fruit.getId(), fruit);
        return fruit;
    }

    @Override
    public Fruit findFruitById(Long id) {
       return fruits.get(id);
    }

    @Override
    public List<Fruit> findAllFruits() {
        return new ArrayList<>(fruits.values());
    }

    //가격과 수량을 갱신해야함.
    @Override
    public void updateFruit(Long id, Fruit updateFruit) {
        Fruit fruit = fruits.get(id);
        if(fruit!=null){
            fruit.setPrice(updateFruit.getPrice());
            fruit.setQuantity(updateFruit.getQuantity());
        }
    }

    @Override
    public void deleteFruit(Long id) {
        fruits.remove(id);
    }

    @Override
    public void deleteAllFruits() {
        fruits.clear();
    }
}
