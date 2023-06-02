package com.music.controller;


import com.music.domain.Song;
import com.music.domain.User;
import com.music.service.ISongService;
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
@RequestMapping("/song")
public class SongController {
    @Autowired
    private ISongService songService;
    @GetMapping
    public Result getAll(){
        List<Song> songs = songService.getAll();
        return new Result(0,"success",songs.size(),songs);
    }
    @GetMapping("/{songid}")
    public Result getById(@PathVariable int songid){
        Song song = songService.getById(songid);
        List<Song> songs = new ArrayList<Song>();
        songs.add(song);
        return new Result(0,"success",songs.size(),songs);
    }
    @DeleteMapping("/{songid}")
    public boolean deleteById(@PathVariable int songid){
        songService.deleteById(songid);
        return true;
    }
    @PostMapping
    public boolean save(@RequestBody Song song){
        songService.save(song);
        return true;
    }
    @PatchMapping
    public boolean updateById(@RequestBody Song song){
        songService.updateById(song);
        return true;
    }
}

