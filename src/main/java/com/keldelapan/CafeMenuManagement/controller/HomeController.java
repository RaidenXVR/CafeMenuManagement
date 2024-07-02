package com.keldelapan.CafeMenuManagement.controller;


import com.keldelapan.CafeMenuManagement.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private FoodService foodService;
    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("foods", foodService.getAllFoods());
        return "home";
    }
}
