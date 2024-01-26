package com.qp.groceryappapis.controller;

import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.ItemDto;
import com.qp.groceryappapis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addItem")
    public ResponseEntity<ItemDto> addItem(@Valid @RequestBody ItemDto itemDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemService.addItem(itemDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto itemDto,@PathVariable("itemId") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemService.updateItem(itemDto, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemId") Integer id) throws ResourceNotFoundException {
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Record Deleted Successfully", HttpStatus.OK));
    }

    @GetMapping("/")
    public ResponseEntity<List<ItemDto>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemService.getAllItems());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("itemId") Integer id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemService.getItemById(id));
    }

}
