package com.alessandro.foxlight.entity;

public class Picture {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PictureService{"
                + "url='" + url + '\''
                + '}';
    }
}
