package com.example.bakingmanagement.Respository;

import com.example.bakingmanagement.Entity.Baking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BakingRepository extends JpaRepository<Baking,Long> {

    boolean existsByProductName(String productName);
    List<Baking> getProductByCategoryIgnoreCase(String category);
}
