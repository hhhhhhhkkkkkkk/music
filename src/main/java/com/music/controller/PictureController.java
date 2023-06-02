package com.music.controller;

import com.music.domain.User;
import com.music.service.ISingerService;
import com.music.service.ISongService;
import com.music.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/pic")
public class PictureController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISongService songService;
    @Autowired
    private ISingerService singerService;
    @RequestMapping("/user/{userid}")
    public void getPicuser(@PathVariable int userid, HttpServletResponse response) throws IOException {
//        String str = "街道";
//        byte[] a = str.getBytes("unicode");
        byte[] by = userService.getById(userid).getUserpic();
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(by);
        outputStream.flush();
    }
    @RequestMapping("/song/{musicid}")
    public void getPicsong(@PathVariable int musicid, HttpServletResponse response) throws IOException {
        byte[] by = songService.getById(musicid).getMusicpic();
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(by);
        outputStream.flush();
    }
    @RequestMapping("/singer/{singerid}")
    public void getPicsinger(@PathVariable int singerid, HttpServletResponse response) throws IOException {
        byte[] by = singerService.getById(singerid).getSingerpic();
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(by);
        outputStream.flush();
    }
}
