package com.qp.groceryappapis.service;

import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.ItemDto;

import java.util.List;

public interface ItemService{

    ItemDto addItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto, Integer id) throws ResourceNotFoundException;
    ItemDto getItemById(Integer id) throws ResourceNotFoundException;
    List<ItemDto> getAllItems();
    void deleteItem(Integer id) throws ResourceNotFoundException;

}
