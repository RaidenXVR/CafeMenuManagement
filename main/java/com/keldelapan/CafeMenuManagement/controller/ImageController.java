package com.keldelapan.CafeMenuManagement.controller;

import com.keldelapan.CafeMenuManagement.entity.Food;
import com.keldelapan.CafeMenuManagement.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ImageController {

    @Autowired
    private FoodService foodService;

    @GetMapping(value = "/foodImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Food food = foodService.getFoodById(id);
        if (food !=null && food.getImage() != null) {
            return ResponseEntity.ok(food.getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
