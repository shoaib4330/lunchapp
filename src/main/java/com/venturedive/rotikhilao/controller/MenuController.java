package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.MenuDTO;
import com.venturedive.rotikhilao.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vendor/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping(value = "/{menuId}")
    public MenuDTO getMenuById (@PathVariable(required = true) Long menuId){
        menuService.
    }
}
