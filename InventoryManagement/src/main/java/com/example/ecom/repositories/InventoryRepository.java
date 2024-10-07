package com.example.ecom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer>{
    public Optional<Inventory> findByProductId(int productId);
}
