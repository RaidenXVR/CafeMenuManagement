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

    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    public Optional<Food> getFoodById(Integer id) {
        return foodRepository.findById(id);
    }

    public void deleteFood(Integer id) {
        foodRepository.deleteById(id);
    }
}
