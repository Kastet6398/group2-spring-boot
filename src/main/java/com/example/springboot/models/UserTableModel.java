package com.example.springboot.models;

import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.HashMap;

public record UserTableModel(HashMap<String, UserModel> users) implements BaseModel {
    public static void createFile() throws IOException {
        Utils.writeJson(Constants.USER_TABLE_FILE, new UserTableModel(new HashMap<>()));
    }
}
