package com.olmez.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olmez.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Query("SELECT t FROM Inventory t WHERE t.skuCode = ?1")
    Optional<Inventory> findBySkuCode(String skuCode);

}
