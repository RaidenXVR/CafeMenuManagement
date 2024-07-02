package com.keldelapan.CafeMenuManagement.repository;

import com.keldelapan.CafeMenuManagement.entity.UserCafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCafe, String> {
}
