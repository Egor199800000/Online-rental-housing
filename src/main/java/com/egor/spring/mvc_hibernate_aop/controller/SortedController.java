package com.egor.spring.mvc_hibernate_aop.controller;


import com.egor.spring.mvc_hibernate_aop.entity.House;
import com.egor.spring.mvc_hibernate_aop.service.HouseService;
import com.egor.spring.mvc_hibernate_aop.service.SearchService;
import com.egor.spring.mvc_hibernate_aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SortedController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    @Autowired
    private SearchService searchService;


   @RequestMapping("/priceAsc") // action
    public String showAllHousesByPriceAsc(Model model){
        model.addAttribute("authUser",userService.getAuthorizedUser());

            List<House> allHouses=searchService.sortAllHousesByPriceAsc();
            model.addAttribute("allHouses",allHouses);
            return "all-houses";
    }

    @RequestMapping("/priceDesc") // action
    public String showAllHousesByPriceDesc(Model model){
        model.addAttribute("authUser",userService.getAuthorizedUser());

        List<House> allHouses=searchService.sortAllHousesByPriceDesc();
        model.addAttribute("allHouses",allHouses);
        return "all-houses";
    }


    @RequestMapping("/priceRange") // action
    public String showHousesByPriceRange(@RequestParam("from") int from,
                                         @RequestParam("to") int to,
                                         Model model){
        model.addAttribute("authUser",userService.getAuthorizedUser());

        List<House> allHouses=searchService.getSearchAllHousesByPriceRange(from,to);
        model.addAttribute("allHouses",allHouses);
        return "all-houses";
    }






}




















