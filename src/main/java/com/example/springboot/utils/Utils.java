package com.example.springboot.utils;

import com.example.springboot.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Utils {
    private static ObjectMapper mapper;
    public static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }
    private Utils() {
        throw new IllegalStateException();
    }
    public static String serialize(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    public static Object deserialize(String json, Class<?> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    public static String read(String filename) throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            return Files.readString(file.toPath());
        } else {
            return "";
        }
    }
    public static void write(String filename, String content) throws IOException {
        Files.write(Path.of(content), content.getBytes());
    }
    public static Object readJson(String filename, Class<?> clazz) throws IOException {
        return getMapper().readValue(new File(filename), clazz);
    }
    public static void delete(String filename) throws IOException {
        Files.delete(Path.of(filename));
    }
    public static void writeJson(String filename, Object object) throws IOException {
        getMapper().writer().writeValue(new File(filename), object);
    }


    public static String encrypt(String plainData, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String lower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String upper = lower.toUpperCase();
    public static final String nums = "0123456789";
    public static final String all = lower + upper + nums;
    public static final char[] chars = all.toCharArray();
    private static Random random;

    private static Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }


    public static String generateRandomString() {
        StringBuilder buf = new StringBuilder();
        for (int id = 0; id < 20; id++)
            buf.append(chars[getRandom().nextInt(all.length())]);
        return buf.toString();
    }

    public static String decrypt(String encryptedData, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String register(UserModel user) throws IOException {
        UserTableModel users = (UserTableModel) readJson(Constants.USER_TABLE_FILE, UserTableModel.class);
        for (UserModel userModel : users.users().values()) {
            if (userModel.username().equals(user.username())) {
                return null;
            }
        }
        UserModel newUser = new UserModel(user.firstName(), user.lastName(), user.username(), user.email(), encrypt(user.password(), Constants.SECRET_KEY));
        String token = encrypt(generateRandomString(), Constants.SECRET_KEY);
        users.users().put(token, newUser);
        writeJson(Constants.USER_TABLE_FILE, users);
        return token;
    }

    public static UserModel getUser(String token) {
        try {
            return ((UserTableModel)readJson(Constants.USER_TABLE_FILE, UserTableModel.class)).users().get(token);
        } catch (Exception e) {
            return null;
        }
    }

    public static String login(LoginUserModel user) throws IOException {
        UserTableModel users = (UserTableModel) readJson(Constants.USER_TABLE_FILE, UserTableModel.class);
        for (Map.Entry<String, UserModel> entry : users.users().entrySet()) {
            if (Objects.equals(entry.getValue().username(), user.username()) && Objects.equals(encrypt(user.password(), Constants.SECRET_KEY), entry.getValue().password())) {
                System.out.println(entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }

    public static boolean createBook(BookModel book) throws IOException {
        BookTableModel books = (BookTableModel) readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class);
        for (BookModel bookModel : books.books()) {
            if (bookModel.name().equals(book.name())) {
                return false;
            }
        }

        books.books().add(new BookModel(book.name(), book.urlOfContent(), book.categoryId(), book.pagesAmount(), book.releaseYear(), books.books().size() + 1));
        writeJson(Constants.BOOK_TABLE_FILE, books);
        return true;
    }

    public static boolean createBookCategory(BookCategoryModel category) throws IOException {
        BookCategoryTableModel categories = (BookCategoryTableModel) readJson(Constants.BOOK_CATEGORY_TABLE_FILE, BookCategoryTableModel.class);
        for (BookCategoryModel bookModel : categories.categories()) {
            if (bookModel.name().equals(category.name())) {
                return false;
            }
        }

        categories.categories().add(new BookCategoryModel(category.name(), categories.categories().size() + 1));
        writeJson(Constants.BOOK_CATEGORY_TABLE_FILE, categories);
        return true;
    }
}
