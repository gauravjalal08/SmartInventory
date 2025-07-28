package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProcessingService {

    @Async
    public CompletableFuture<String> processOrder(Long orderId){
        try {
            Thread.sleep(2000);
            System.out.println("Processing order ID: " + orderId + " - Thread: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return CompletableFuture.completedFuture("Order " + orderId + " processed");
    }
}
