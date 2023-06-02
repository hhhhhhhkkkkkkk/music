package com.music.controller;


import com.music.dao.SingerDao;
import com.music.domain.Singer;
import com.music.domain.Song;
import com.music.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private ISingerService singerService;
    @GetMapping
    public Result getAll(){
        List<Singer> singers = singerService.getAll();
        return new Result(0,"success",singers.size(),singers);
    }
    @GetMapping("/{singerid}")
    public Result getById(@PathVariable int singerid){
        Singer singer = singerService.getById(singerid);
        List<Singer> singers = new ArrayList<Singer>();
        singers.add(singer);
        return new Result(0,"success",singers.size(),singers);
    }
    @DeleteMapping("/{singerid}")
    public boolean deleteById(@PathVariable int singerid){
        singerService.deleteById(singerid);
        return true;
    }
    @PostMapping
    public boolean save(@RequestBody Singer singer){
        singerService.save(singer);
        return true;
    }
    @PatchMapping
    public boolean updateById(@RequestBody Singer singer){
        singerService.updateById(singer);
        return true;
    }
}

