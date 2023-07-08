package com.egor.spring.mvc_hibernate_aop.controller;

import com.egor.spring.mvc_hibernate_aop.entity.House;
import com.egor.spring.mvc_hibernate_aop.entity.User;
import com.egor.spring.mvc_hibernate_aop.exceptionHandling.NoAuthorizedUserException;
import com.egor.spring.mvc_hibernate_aop.service.HouseService;
import com.egor.spring.mvc_hibernate_aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HouseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @RequestMapping("ownersHouse")
    public String houseInfo(@RequestParam("houseId") int id, Model model){

        House house=houseService.getHouse(id);
        model.addAttribute("houseDescr",house);
        return "house-info";
    }

    @RequestMapping("/addNewHouse")
    public String addNewHouse(Model model){
            User user=userService.getAuthorizedUser();
            if (user==null){
                throw new NoAuthorizedUserException("log in to add a house rental ad");
            }
        House house=new House();
        model.addAttribute("house",house);
        return "house-param";
    }

    @RequestMapping("/saveNewHouse")
    public String saveHouse(@Valid @ModelAttribute("house") House house,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.err.println("Validation Error");
            return "house-param";
        }
            User user=userService.getAuthorizedUser();
            userService.addHouseToListHousesOwner(house,user);
            System.err.println("House add");

        return "redirect:/";
    }

    //TODO:Доработать
    @RequestMapping("rentAHouse") //-кнопка
    public String rent(@RequestParam("houseId") int id, Model model){
//сюда должны попасть id дома который мы смотрим
        House house=houseService.getHouse(id);
        User user=userService.getAuthorizedUser();
        userService.addHouseToListHousesTenant(house,user);
        model.addAttribute("rentAHouse",house);
        model.addAttribute("tenant",user);
        return "rent";
    }

}
