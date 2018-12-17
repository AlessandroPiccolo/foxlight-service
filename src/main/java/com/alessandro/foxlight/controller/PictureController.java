package com.alessandro.foxlight.controller;

import java.io.IOException;

import com.alessandro.foxlight.entity.Picture;
import com.alessandro.foxlight.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/fox")
    public Picture getAllCourses() {
        System.out.println("Fetching random picture of fox");

        Picture foxPicture = null;
        try {
            foxPicture = pictureService.findFoxPicture();
        } catch (IOException ex) {
            System.out.println("Internal error!");
        }

        return foxPicture;
    }
}
