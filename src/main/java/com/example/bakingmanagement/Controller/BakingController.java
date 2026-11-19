package com.example.bakingmanagement.Controller;

import com.example.bakingmanagement.DTO.BakingRequest;
import com.example.bakingmanagement.DTO.BakingResponse;
import com.example.bakingmanagement.Service.BakingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baking")
public class BakingController {

    private final BakingService bakingService;

    public BakingController(BakingService bakingService) {
        this.bakingService = bakingService;
    }

    @PostMapping
    public ResponseEntity<BakingResponse> createProduct(@Valid @RequestBody BakingRequest request){
        BakingResponse response = bakingService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BakingResponse>> getAllProducts(){
        return ResponseEntity.ok(bakingService.getAllProducts());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<BakingResponse>> getProductByCategory(@PathVariable String category){
        return ResponseEntity.ok(bakingService.getProductsByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BakingResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody BakingRequest request){
        return ResponseEntity.ok(bakingService.updateProduct(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        bakingService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
