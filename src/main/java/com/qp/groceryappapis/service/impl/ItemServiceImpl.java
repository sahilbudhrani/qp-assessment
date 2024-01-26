package com.qp.groceryappapis.service.impl;

import com.qp.groceryappapis.entity.Item;
import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.ItemDto;
import com.qp.groceryappapis.repository.ItemRepository;
import com.qp.groceryappapis.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        return modelMapper.map(itemRepository.save(item), ItemDto.class);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, Integer id) throws ResourceNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "ID", String.valueOf(id)));
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setAvailableInventory(itemDto.getAvailableInventory());
        return modelMapper.map(itemRepository.save(item), ItemDto.class);
    }

    @Override
    public ItemDto getItemById(Integer id) throws ResourceNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "ID", String.valueOf(id)));
        return modelMapper.map(item, ItemDto.class);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = items.stream().map(item -> modelMapper.map(item, ItemDto.class)).collect(Collectors.toList());
        return itemDtos;
    }

    @Override
    public void deleteItem(Integer id) throws ResourceNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "ID", String.valueOf(id)));
        itemRepository.delete(item);
    }
}
