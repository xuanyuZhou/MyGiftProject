package com.example.dllo.mygiftproject.model.bean;

/**
 * Created by dllo on 16/7/14.
 */
public class LocalHotFmGvBean {
    String name, likesCount, imageUrl, price;

    public LocalHotFmGvBean() {
    }

    public String getName() {
        return name;
    }

    public LocalHotFmGvBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public LocalHotFmGvBean setLikesCount(String likesCount) {
        this.likesCount = likesCount;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalHotFmGvBean setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public LocalHotFmGvBean setPrice(String price) {
        this.price = price;
        return this;
    }
}
