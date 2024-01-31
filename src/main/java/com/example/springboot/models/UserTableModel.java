package com.example.springboot.models;

import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.HashMap;

public class UserTableModel implements BaseModel {
    public void setUsers(HashMap<String, UserModel> users) {
        this.users = users;
    }
    public UserTableModel() {

    }

    private HashMap<String, UserModel> users;

    public UserTableModel(HashMap<String, UserModel> users) {
        this.users = users;
    }

    public static void createFile() throws IOException {
        Utils.writeJson(Constants.USER_TABLE_FILE, new UserTableModel(new HashMap<>()));
    }

    public HashMap<String, UserModel> getUsers() {
        return users;
    }
}
