package com.example.fruitshop.controller;

import com.example.fruitshop.model.Fruit;
import com.example.fruitshop.service.FruitService;
import com.example.fruitshop.service.FruitServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;
    public FruitController(FruitService fruitService){
        this.fruitService = fruitService;
    }

    //모든 과일 목록 출력
    @GetMapping
    public String showAllFruits(Model model){
        model.addAttribute("fruits", fruitService.getAllFruits());
        return "fruits/list";
    }

    //선택 과일 출력
    @GetMapping("/{id}")
    public String showFruit(Model model, @PathVariable Long id){
        model.addAttribute("fruit", fruitService.getFruitById(id));
        return "fruits/detail";
    }
}
