package com.alessandro.foxlight.service;

import com.alessandro.foxlight.entity.Picture;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PictureService {

    @Autowired
    private ObjectMapper mapper;

    private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&gws_rd=cr&q=";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";

    public Picture findFoxPicture() throws IOException {
        Picture response = new Picture();
        response.setUrl(getRandomGoogleImage("fox"));
        return response;
    }

    private String getRandomGoogleImage(final String searchTerm) throws IOException {

        Document doc;
        try {
            doc = Jsoup.connect(GOOGLE_SEARCH_URL + searchTerm).userAgent(USER_AGENT).referrer("https://www.google.com/").get();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }

        List<String> resultUrls = extractResultUrls(doc);

        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        return resultUrls.get(randomNum);
    }

    private List<String> extractResultUrls(final Document doc) {
        List<String> resultUrls = new ArrayList<>();
        Elements elements = doc.select("div.rg_meta");
        for (Element element : elements) {
            if (element.childNodeSize() > 0) {
                try {
                    JsonNode imgElem = mapper.readTree(element.childNode(0).toString());
                    if (imgElem.get("ou") != null) {
                        resultUrls.add(imgElem.get("ou").asText());
                    }
                } catch (IOException ex) {
                    System.out.println("SEVERE Failed to parse: " + element.childNode(0).toString());
                }
            }
        }
        return resultUrls;
    }

}
