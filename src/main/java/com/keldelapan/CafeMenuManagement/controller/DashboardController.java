package com.keldelapan.CafeMenuManagement.controller;


import com.keldelapan.CafeMenuManagement.entity.Food;
import com.keldelapan.CafeMenuManagement.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class DashboardController {
    @Autowired
    private FoodService foodService;
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("foods",foodService.getAllFoods() );
        return "dashboard";
    }

    @GetMapping("/addFood")
    public String addFoodForm(Model model) {
        model.addAttribute("food", new Food());
        return "/dashboard/addFood";
    }
    @PostMapping("/saveFood")
    public String saveFood(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile
    ) {
        Food food = new Food();
        food.setName(name);
        food.setPrice(price);
        food.setType(type);
        food.setDescription(description);

        try {
            if (!imageFile.isEmpty()) {
                food.setImage(imageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        foodService.saveFood(food);
        return "redirect:/dashboard";
    }

    @GetMapping("/editFood/{id}")
    public String editFoodForm(@PathVariable Integer id, Model model) {
        Food food = foodService.getFoodById(id);
        if (food != null) {
            model.addAttribute("food", food);
            return "dashboard/editFood";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/updateFood")
    public String updateFood(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile
    ) {
        Food food = foodService.getFoodById(id);
        if (food == null) {
            return "redirect:/dashboard";
        }

        food.setName(name);
        food.setPrice(price);
        food.setType(type);
        food.setDescription(description);

        try {
            if (!imageFile.isEmpty()) {
                food.setImage(imageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        foodService.saveFood(food);
        return "redirect:/dashboard";
    }

    @GetMapping("/deleteFood/{id}")
    public String deleteFood(@PathVariable Integer id) {
        foodService.deleteFood(id);
        return "redirect:/dashboard";
    }
}
