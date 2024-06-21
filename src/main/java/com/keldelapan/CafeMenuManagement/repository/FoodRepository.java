package com.keldelapan.CafeMenuManagement.repository;

import com.keldelapan.CafeMenuManagement.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
