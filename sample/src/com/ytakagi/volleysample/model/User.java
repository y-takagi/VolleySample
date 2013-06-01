
package com.ytakagi.volleysample.model;

import java.util.HashMap;

import lombok.Data;

@Data
public class User {
    private int id;
    private String screenName;
    private HashMap<String, String> profileImageUrl;
}
