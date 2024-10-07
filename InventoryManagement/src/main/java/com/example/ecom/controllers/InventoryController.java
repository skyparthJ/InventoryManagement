package com.example.ecom.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.ecom.dtos.CreateOrUpdateRequestDto;
import com.example.ecom.dtos.CreateOrUpdateResponseDto;
import com.example.ecom.dtos.DeleteInventoryRequestDto;
import com.example.ecom.dtos.DeleteInventoryResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.models.Inventory;
import com.example.ecom.services.InventoryService;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto){

        CreateOrUpdateResponseDto responseDto = new CreateOrUpdateResponseDto();

        try{
            Inventory inventory = inventoryService.createOrUpdateInventory(requestDto.getUserId(), 
            requestDto.getProductId(), requestDto.getQuantity());
            responseDto.setInventory(inventory);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception exception){
            responseDto.setInventory(null);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            
        }

        return responseDto;
    }

    public DeleteInventoryResponseDto deleteInventory(DeleteInventoryRequestDto requestDto){
        DeleteInventoryResponseDto responseDto = new DeleteInventoryResponseDto();

        try{
            inventoryService.deleteInventory(requestDto.getUserId(), requestDto.getProductId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception exception){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            exception.printStackTrace();
        }

        return responseDto;
    }


}
