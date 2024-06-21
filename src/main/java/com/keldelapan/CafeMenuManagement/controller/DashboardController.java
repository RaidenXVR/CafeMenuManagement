package com.keldelapan.CafeMenuManagement.controller;


import com.keldelapan.CafeMenuManagement.entity.Food;
import com.keldelapan.CafeMenuManagement.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String saveFood(@ModelAttribute Food food, @RequestParam("image") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                Path path = Paths.get("uploads/" + imageFile.getOriginalFilename());
                Files.write(path, bytes);
                food.setImageUrl("/uploads/" + imageFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        foodService.saveFood(food);
        return "redirect:/dashboard";
    }
}
