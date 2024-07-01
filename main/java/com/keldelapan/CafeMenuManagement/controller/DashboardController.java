package com.keldelapan.CafeMenuManagement.controller;


import com.keldelapan.CafeMenuManagement.entity.Food;
import com.keldelapan.CafeMenuManagement.entity.UserCafe;
import com.keldelapan.CafeMenuManagement.service.FoodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class DashboardController {
    @Autowired
    private FoodService foodService;
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){
        UserCafe user = (UserCafe) session.getAttribute("user");

        if (!user.getIs_admin()){
            return "redirect:/home";
        }

        model.addAttribute("foods",foodService.getAllFoods() );
        return "admin/dashboard";
    }

    @GetMapping("/addFood")
    public String addFoodForm(Model model, HttpSession session) {
        UserCafe user = (UserCafe) session.getAttribute("user");

        if (!user.getIs_admin()){
            return "redirect:/home";
        }
        model.addAttribute("food", new Food());
        return "admin/dashboard/addFood";
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
    public String editFoodForm(@PathVariable Integer id, Model model, HttpSession session) {

        UserCafe user = (UserCafe) session.getAttribute("user");

        if (!user.getIs_admin()){
            return "redirect:/home";
        }

        Food food = foodService.getFoodById(id);
        if (food != null) {
            model.addAttribute("food", food);
            return "admin/dashboard/editFood";
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
