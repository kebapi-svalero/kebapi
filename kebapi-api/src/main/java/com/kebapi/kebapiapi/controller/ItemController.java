package com.kebapi.kebapiapi.controller;

import com.kebapi.kebapiapi.domain.Item;
import com.kebapi.kebapiapi.domain.dto.ItemInDto;
import com.kebapi.kebapiapi.domain.dto.ItemOutDto;
import com.kebapi.kebapiapi.exception.ItemNotFoundException;
import com.kebapi.kebapiapi.service.ItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @GetMapping
    public ResponseEntity<List<ItemOutDto>> getAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "description", defaultValue = "") String description)  {
        List<ItemOutDto> items = itemService.getAll(name, description);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable long itemId)  throws ItemNotFoundException {
        Item item = itemService.get(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemOutDto> addMenuItem(@RequestBody @Valid ItemInDto item) {
        ItemOutDto newItem = itemService.add(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeMenuItem(@PathVariable long itemId) throws ItemNotFoundException {
        itemService.remove(itemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemOutDto> modifyItem(@PathVariable @Valid long itemId, @RequestBody ItemInDto item) throws ItemNotFoundException {
        ItemOutDto modifiedItem = itemService.modify(itemId, item);
        return new ResponseEntity<>(modifiedItem, HttpStatus.OK);
    }
}
