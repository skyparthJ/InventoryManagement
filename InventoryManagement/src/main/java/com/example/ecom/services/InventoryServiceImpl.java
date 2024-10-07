package com.example.ecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.exceptions.UnAuthorizedAccessException;
import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Inventory;
import com.example.ecom.models.Product;
import com.example.ecom.models.User;
import com.example.ecom.models.UserType;
import com.example.ecom.repositories.InventoryRepository;
import com.example.ecom.repositories.ProductRepository;
import com.example.ecom.repositories.UserRepository;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Inventory createOrUpdateInventory(int userId, int productId, int quantity)
            throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        if(user.getUserType() != UserType.ADMIN){
            throw new UnAuthorizedAccessException("Unauthorized Access");
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }

        Product product = productOptional.get();

        Optional<Inventory> optionalInventory = inventoryRepository.findByProductId(productId);
        Inventory inventory = null;
        if(optionalInventory.isEmpty()){
            inventory = new Inventory();
            inventory.setQuantity(quantity);
        }else{
            inventory = optionalInventory.get();
            inventory.setQuantity(inventory.getQuantity() + quantity);
        }
        inventory.setProduct(product);
            
        inventoryRepository.save(inventory);

        return inventory;
    }

    @Override
    public void deleteInventory(int userId, int productId) throws UserNotFoundException, UnAuthorizedAccessException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        if(user.getUserType() != UserType.ADMIN){
            throw new UnAuthorizedAccessException("Unauthorized Access");
        }

        Optional<Inventory> inventoryOptional = inventoryRepository.findByProductId(productId);
        if(!inventoryOptional.isEmpty())
            inventoryRepository.delete(inventoryOptional.get());
    }
    
}
