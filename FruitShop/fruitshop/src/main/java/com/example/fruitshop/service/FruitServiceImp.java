package com.example.fruitshop.service;

import com.example.fruitshop.model.Fruit;
import com.example.fruitshop.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImp implements FruitService {
    private final FruitRepository fruitRepository;

    //스프링이 @Repository로 등록된 MemoryFruitRepository를 자동으로 인터페이스 매개변수에 등록해줌.
    public FruitServiceImp(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public Fruit addFruit(Fruit fruit){
        return fruitRepository.saveFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Long id) {
        return fruitRepository.findFruitById(id);
    }

    @Override
    public List<Fruit> getAllFruits(){
        return fruitRepository.findAllFruits();
    }

    @Override
    public void updateFruit(Long id, Fruit updateFruit){
        fruitRepository.updateFruit(id, updateFruit);
    }

    @Override
    public void deleteFruit(Long id) {
        fruitRepository.deleteFruit(id);
    }

    @Override
    public void deleteAllFruits(){
        fruitRepository.deleteAllFruits();
    }
}
