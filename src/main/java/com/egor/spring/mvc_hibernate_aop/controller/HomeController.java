package com.egor.spring.mvc_hibernate_aop.controller;

import com.egor.spring.mvc_hibernate_aop.entity.House;
import com.egor.spring.mvc_hibernate_aop.entity.User;
import com.egor.spring.mvc_hibernate_aop.service.HouseService;
import com.egor.spring.mvc_hibernate_aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String showAllHouses(Model model){
        User user=userService.getAuthorizedUser();
        model.addAttribute("authUser",user);
        List<House> allHouses=houseService.getAllHouses();
        model.addAttribute("allHouses",allHouses);
        return "all-houses";
    }


    @RequestMapping("/showDetails")
    public String houseInfo(@RequestParam("houseId") int id, Model model){
        House house=houseService.getHouse(id);
        model.addAttribute("houseDescr",house);
        return "house-info";
    }

    @RequestMapping("/homePage")
    public String homePage(){
        return "redirect:/";
    }




    @RequestMapping("/logOut")
    public String logOut(Model model){
        User user=userService.getAuthorizedUser();
        user.setAuthorized(false);
        userService.saveUser(user);
        model.addAttribute("authUser",null);
        return "redirect:/";
    }

}
