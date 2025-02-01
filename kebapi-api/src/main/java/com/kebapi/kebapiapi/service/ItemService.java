package com.kebapi.kebapiapi.service;



import com.kebapi.kebapiapi.domain.Customer;
import com.kebapi.kebapiapi.domain.Item;
import com.kebapi.kebapiapi.domain.dto.CustomerOutDto;
import com.kebapi.kebapiapi.domain.dto.CustomerRegistrationDto;
import com.kebapi.kebapiapi.domain.dto.ItemOutDto;
import com.kebapi.kebapiapi.domain.dto.ItemInDto;
import com.kebapi.kebapiapi.exception.ItemNotFoundException;
import com.kebapi.kebapiapi.repository.ItemRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ItemOutDto> getAll(String name, String description) {
        List<Item> itemList;

        if (name.isEmpty() && description.isEmpty()) {
            itemList = itemRepository.findAll();
        } else if (name.isEmpty()) {
            itemList = itemRepository.findByDescription(description);
        } else if (description.isEmpty()) {
            itemList = itemRepository.findByName(name);
        } else {
            itemList = itemRepository.findByNameAndDescription(name, description);
        }

        return modelMapper.map(itemList, new TypeToken<List<ItemOutDto>>() {
        }.getType());
    }

    public Item get(long id) throws ItemNotFoundException {
        return itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }


    public ItemOutDto add(ItemInDto itemInDto) {
        Item item = modelMapper.map(itemInDto, Item.class);
        Item newItem = itemRepository.save(item);

        return modelMapper.map(newItem, ItemOutDto.class);
    }

    public ItemOutDto modify(long itemId, ItemInDto itemInDto) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotFoundException::new);

        modelMapper.map(itemInDto, item);
        itemRepository.save(item);

        return modelMapper.map(item, ItemOutDto.class);
    }

    public void remove(long menuItemId) throws ItemNotFoundException {
        itemRepository.findById(menuItemId).orElseThrow(ItemNotFoundException::new);
        itemRepository.deleteById(menuItemId);
    }
}
