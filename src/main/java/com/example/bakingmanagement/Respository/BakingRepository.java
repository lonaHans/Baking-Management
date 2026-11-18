package com.example.bakingmanagement.Respository;

import com.example.bakingmanagement.Entity.Baking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BakingRepository extends JpaRepository<Baking,Long> {

    boolean existsByProductName(String productName);
}
