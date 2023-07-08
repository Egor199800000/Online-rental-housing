package com.egor.spring.mvc_hibernate_aop.controller;

import com.egor.spring.mvc_hibernate_aop.entity.House;
import com.egor.spring.mvc_hibernate_aop.entity.User;
import com.egor.spring.mvc_hibernate_aop.exceptionHandling.AlreadyAuthorizedException;
import com.egor.spring.mvc_hibernate_aop.exceptionHandling.NoAuthorizedUserException;
import com.egor.spring.mvc_hibernate_aop.exceptionHandling.NoSuchEntityException;
import com.egor.spring.mvc_hibernate_aop.service.HouseService;
import com.egor.spring.mvc_hibernate_aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @RequestMapping("/addNewUser") //-action
    public String addNewUser(Model model){
        User otherUser=userService.getAuthorizedUser();
        if (otherUser!=null){
            throw new AlreadyAuthorizedException("you are already logged in");
        }

        User user=new User();
        model.addAttribute("user",user);
        return "user-info";
    }


    @RequestMapping("/saveNewUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "user-info";
        }

        //TODO обработать исключение
        String email=user.getEmail();
        userService.isUniqueEmail(email);

        user.setDeleted(false);
        user.setEnable(true);
        user.setAuthorized(false);
        user.setRating(5);
        userService.saveUser(user);
        return "redirect:/";
    }




    @GetMapping("/signIn") //-кнопка
    public String signIn(Model model){
        User otherUser=userService.getAuthorizedUser();
        if (otherUser!=null){
            throw new AlreadyAuthorizedException("you are already logged in");
        }
        User user=new User();
        model.addAttribute("user",user);
        return "signIn";
    }

    @PostMapping("authorized") //action
    public String authorized(@ModelAttribute("user") User user,
                             Model model){
        User user1=null;
        User user2=null;
        try {
             user1= userService.getUserByEmail(user.getEmail());
             user2= userService.getUserByPassword(user.getPassword());
        }catch (IndexOutOfBoundsException e){
             throw new IndexOutOfBoundsException("Incorrect login or password");
        }


        if (user1.getId()==user2.getId()){
            System.out.println("Users equals");
            user=userService.getUser(user1.getId());
            user.setAuthorized(true);
            userService.saveUser(user);
            model.addAttribute("authUser",user);
            return "success";
        }
        return "redirect:/";
    }



    @GetMapping("/ownerInfo") //-кнопка
    public String OwnerInformation(@RequestParam("houseId") int id,Model model){
        House house=houseService.getHouse(id);
        User user=house.getOwner();
        System.err.println(user.getName()+" -owner");
        model.addAttribute("owner",user);
        return "owner-information";
    }


    @RequestMapping("/profile")
    public String profile(Model model){
        User user=userService.getAuthorizedUser();
       if (user==null){
           throw new NoAuthorizedUserException("Please log in first to see your account information");
       }
        model.addAttribute("authUser",user);
        return "profile";
    }

}
