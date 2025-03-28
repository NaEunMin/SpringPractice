package com.example.demo.Controller;

import com.example.demo.Service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller -> 웹 페이지를 렌더링하기 위한 컨트롤러, 뷰 이름을 반환합니다.
//RestController -> RESTful API를 위한 컨트롤러로, JSON 또는 XML 데이터를 반환합니다.
// @Controller와 @ResponseBody가 결합된 형태
@Controller
public class GreetingController {
    private final GreetingService greetingService;

    @Autowired //spring이 greetingServie의 구현체인 GreetingServiceLmp를 찾아서 GreetingService 타입으로 주입
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public String greet(Model model) {
        model.addAttribute("message", greetingService.greet());
        return "hello";
    }

}
