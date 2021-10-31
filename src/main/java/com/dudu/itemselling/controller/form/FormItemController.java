package com.dudu.itemselling.controller.form;

import com.dudu.itemselling.argumentresolver.SignIn;
import com.dudu.itemselling.domain.Item;
import com.dudu.itemselling.domain.ItemType;
import com.dudu.itemselling.domain.User;
import com.dudu.itemselling.dto.ItemDTO;
import com.dudu.itemselling.dto.ItemsListDTO;
import com.dudu.itemselling.dto.UserDTO;
import com.dudu.itemselling.repository.ItemRepository;

import com.dudu.itemselling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }


    @GetMapping
    public String items(@SignIn UserDTO user, Model model) {
        if (user == null) {
            return "/selling/signin";
        }

        model.addAttribute("user", user);

        List<ItemsListDTO> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model, @SignIn UserDTO user) {
        ItemDTO item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        if(user.getId().equals(item.getUserId())){
            return "form/item";
        }
        return "form/onlyReadableItem";


    }

    @GetMapping("/add")
    public String addForm(@SignIn UserDTO user, Model model) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setUserName(user.getName());
        model.addAttribute("item", itemDTO);
        return "form/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemDTO itemDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @SignIn UserDTO user) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "form/addForm";
        }

        Item item = new Item(itemDTO.getId(), itemDTO.getItemName(), null, itemDTO.getPrice(), itemDTO.getQuantity()
        , itemDTO.getSold(), itemDTO.getRegions(), itemDTO.getItemType());

        User findUser = userRepository.findById(user.getId());


        Item savedItem = itemRepository.save(item, findUser);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model, @SignIn UserDTO user) {
        ItemDTO item = itemRepository.findById(itemId);

        if(!user.getId().equals(item.getUserId())){ // 둘다 다르면
            return "redirect:/form/items/{itemId}";
        }
        model.addAttribute("item", item);

        return "form/editForm";
    }

    @Transactional
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemDTO item, @SignIn UserDTO user) {

        Item findItem = itemRepository.findByItemId(itemId);
        User findUser = findItem.getUser();
        if(!user.getId().equals(findUser.getId())){ // 둘다 다르면
            return "redirect:/form/items/{itemId}";
        }

        findItem.setItemType(item.getItemType());
        findItem.setPrice(item.getPrice());
        findItem.setQuantity(item.getQuantity());
        findItem.setRegions(item.getRegions());
        findItem.setSold(item.getSold());
        findItem.setItemName(item.getItemName());


        return "redirect:/form/items/{itemId}";
    }

}

