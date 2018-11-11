package org.piy.foxlight.Service;

import org.piy.foxlight.Entity.Picture;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

  private static String FOX_URL = "https://i.pinimg.com/originals/63/2c/2f/632c2fa5185f14ea2cb3f2c7cee1c9f3.jpg";

  public Picture findFoxPicture() {
    Picture response = new Picture();
    response.setUrl(FOX_URL);
    return response;
  }

}
