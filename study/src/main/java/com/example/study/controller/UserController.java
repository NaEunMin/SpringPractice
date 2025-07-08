package com.example.study.controller;

import com.example.study.domain.user.dto.UserReq;
import com.example.study.domain.user.dto.UserRes;
import com.example.study.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    //회원가입 페이지 호출
    @GetMapping("/user/join")
    public String joinPage() {
        return "join";
    }

    //회원 가입 진행
    @PostMapping("/user/join")
    public String joinProcess(UserReq userReq){
        userService.createUser(userReq);
        return "redirect:/login";
    }

    //회원수정 페이지 호출
    @GetMapping("/user/update/{username}")
    public String updatePage(@PathVariable String username, Model model){
        //본인 또는 admin 권한만 접근 가능
        if(userService.isAccess(username)){
            UserRes userRes = userService.readUser(username);
            model.addAttribute("user", userRes);
            return "update";
        }
        return "redirect:/login";
    }

    //회원정보 수정
    @PostMapping("/user/update/{username}")
    public String updateProcess(@PathVariable String username, UserReq userReq){

        //본인 또는 admin 권한만 접근 가능
        if(userService.isAccess(username)){
            userService.updateUser(userReq, username);
        }

        return "redirect:/user/update/" + username;
    }

}
