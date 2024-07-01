package com.keldelapan.CafeMenuManagement.controller;

import com.keldelapan.CafeMenuManagement.entity.UserCafe;
import com.keldelapan.CafeMenuManagement.exceptions.UserExistException;
import com.keldelapan.CafeMenuManagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LoginRegisController {

    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String regis(Model model){
        model.addAttribute("error","");
        return "register";
    }

    @PostMapping("/postRegis")
    public String post_register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ){

        UserCafe user = new UserCafe();
        user.setUsername(username);
        user.setPwd(password);
        try {
            userService.saveUser(user);
        }
        catch (UserExistException e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("error", "");
        return "login";
    }

    @PostMapping("/postLogin")
    public String postLogin(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model){

        UserCafe user = userService.getUserById(username);
        if (user == null){
            model.addAttribute("error", "No Username "+username +" found");
            return "login";
        }
        else if (!Objects.equals(user.getPwd(), password)) {
            model.addAttribute("error", "Wrong Password");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/home";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "home";
    }

}
