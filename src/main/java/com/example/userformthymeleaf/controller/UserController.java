package com.example.userformthymeleaf.controller;
import com.example.userformthymeleaf.model.User;
import com.example.userformthymeleaf.model.UserRole;
import com.example.userformthymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "showusers";
    }

    //Display the user registration form
    @GetMapping("/register")
    public String showUserRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", UserRole.values());
        return "user-registration-form";
    }

    //Handle the form submission
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/user/users";
    }

    //Display the edit form
    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable int userId, Model model){
        User user = userService.getUserById(userId);

        model.addAttribute("user", user);
        model.addAttribute("userRoles", UserRole.values());
        return "user-edit-form";
    }

    //Handle the form submission
    @PostMapping("/update")
    public String editUser(@ModelAttribute User user){
        userService.updateUser(user);
        return "redirect:/user/users";
    }

}
