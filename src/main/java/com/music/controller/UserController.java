package com.music.controller;


import com.music.domain.User;
import com.music.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @GetMapping
    public Result getAll(){
        List<User> users = userService.getAll();
        return new Result(0,"success",users.size(),users);
    }
    @GetMapping("/{userid}")
    public Result getById(@PathVariable int userid){
        User user = userService.getById(userid);
        List<User> users = new ArrayList<User>();
        users.add(user);
        return new Result(0,"success",users.size(),users);
    }
    @DeleteMapping("/{userid}")
    public boolean deleteById(@PathVariable int userid){
        userService.deleteById(userid);
        return true;
    }
    @PostMapping
    public boolean save(@RequestBody User user){
        userService.save(user);
        return true;
    }
    @PatchMapping
    public boolean updateById(@RequestBody User user){
        userService.updateById(user);
        return true;
    }
}

