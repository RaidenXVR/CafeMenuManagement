package com.keldelapan.CafeMenuManagement.service;

import com.keldelapan.CafeMenuManagement.entity.Food;
import com.keldelapan.CafeMenuManagement.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;


    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public void saveFood(Food food) {
        foodRepository.save(food);

    }


    public Food getFoodById(Integer id) {
        return foodRepository.findById(id).orElse(null);
    }

    public void deleteFood(Integer id) {
        foodRepository.deleteById(id);
    }
}
