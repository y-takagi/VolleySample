
package com.ytakagi.volleysample.model;

import java.util.HashMap;

import lombok.Data;

@Data
public class Item {
    private int id;
    private String title;
    private String description;
    private HashMap<String, String>[] imageUrls;
    private User user;
}
