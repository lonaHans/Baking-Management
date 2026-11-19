package com.example.bakingmanagement.Service;

import com.example.bakingmanagement.DTO.BakingRequest;
import com.example.bakingmanagement.DTO.BakingResponse;
import com.example.bakingmanagement.Entity.Baking;
import com.example.bakingmanagement.Exceptions.DuplicateResourceException;
import com.example.bakingmanagement.Exceptions.ResourceNotFoundException;
import com.example.bakingmanagement.Respository.BakingRepository;
import com.example.bakingmanagement.Enum.StockStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BakingService {

    private final BakingRepository bakingRepository;

    public BakingService(BakingRepository bakingRepository) {
        this.bakingRepository = bakingRepository;
    }

    @Transactional
    public BakingResponse createProduct(BakingRequest request){

        if(bakingRepository.existsByProductName(request.productName())){
            throw new DuplicateResourceException("The product name already exits");
        }

        Baking baking =  new Baking();
        baking.setProductName(request.productName());
        baking.setCategory(request.category());
        baking.setPrice(request.price());
        baking.setQuantity(request.quantity());
        baking.setLocation(request.location());
        baking.setBakingDate(request.bakingDate());
        baking.setStockStatus(calculateStatus(request.quantity()));


        Baking savedBaking = bakingRepository.save(baking);

        return mapToResponse(savedBaking);

    }


    @Transactional(readOnly = true)
    public List<BakingResponse> getAllProducts(){
        return bakingRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Transactional
    public BakingResponse updateProduct(Long id, BakingRequest request){
        Baking baking = bakingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        baking.setProductName(request.productName());
        baking.setCategory(request.category());
        baking.setPrice(request.price());
        baking.setQuantity(request.quantity());
        baking.setLocation(request.location());
        baking.setBakingDate(request.bakingDate());
        baking.setStockStatus(calculateStatus(request.quantity()));


        Baking savedBaking = bakingRepository.save(baking);

        return mapToResponse(savedBaking);

    }

    @Transactional
    public void deleteProduct(Long id){
        Baking baking = bakingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        bakingRepository.delete(baking);

    }


    private StockStatus calculateStatus(int quantity){
        if (quantity == 0) {
            return StockStatus.OUT_OF_STOCK;
        } else if (quantity <= 5) {
            return StockStatus.LOW_STOCK;
        } else {
            return StockStatus.IN_STOCK;
        }
    }

    private BakingResponse mapToResponse(Baking baking){
        return new BakingResponse(
                baking.getId(),
                baking.getProductName(),
                baking.getCategory(),
                baking.getPrice(),
                baking.getQuantity(),
                baking.getLocation(),
                baking.getBakingDate(),
                baking.getStockStatus()
        );
    }
}
