package org.piy.foxlight.Controller;

import org.piy.foxlight.Entity.Picture;
import org.piy.foxlight.Service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pictures")
public class PictureController {

  @Autowired
  private PictureService pictureService;

  @RequestMapping("/fox")
  public Picture getAllCourses() {
    return pictureService.findFoxPicture();
  }
}
