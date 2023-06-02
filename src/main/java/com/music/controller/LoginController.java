package com.music.controller;

import com.music.domain.Admin;
import com.music.domain.User;
import com.music.service.IAdminService;
import com.music.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAdminService adminService;
    @RequestMapping("/user")
    public boolean login(@RequestBody User user){
        User user1 = userService.login(user);
        if(user1!=null){
            return true;
        }
        return false;
    }
    @RequestMapping("/admin")
    public boolean login(@RequestBody Admin admin){
        Admin admin1 = adminService.login(admin);
        if(admin1!=null){
            return true;
        }
        return false;
    }
}
