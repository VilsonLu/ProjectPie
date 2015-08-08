package com.example.vilso.projectpie;

public class MenuItemModel {
    private int img_src;

    private int img_src_selected;
    private String title;

    public MenuItemModel(int img_src, int img_src_selected, String title) {
        this.img_src = img_src;
        this.img_src_selected = img_src_selected;
        this.title = title;
    }

    public int getImg_src() {
        return img_src;
    }

    public int getImg_src_selected() {
        return img_src_selected;
    }

    public String getTitle() {
        return title;
    }

}
