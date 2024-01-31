package com.example.springboot.utils;

import com.example.springboot.models.*;

import com.example.springboot.models.books.*;
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

@SuppressWarnings("unused")
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
            return null;
        }
    }

    public static String register(UserModel user) throws IOException {
        UserTableModel users = (UserTableModel) readJson(Constants.USER_TABLE_FILE, UserTableModel.class);
        for (UserModel userModel : users.getUsers().values()) {
            if (userModel.getUsername().equals(user.getUsername())) {
                return null;
            }
        }
        UserModel newUser = new UserModel(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), encrypt(user.getPassword(), Constants.SECRET_KEY), users.getUsers().size() + 1);
        String token = encrypt(generateRandomString(), Constants.SECRET_KEY);
        users.getUsers().put(token, newUser);
        writeJson(Constants.USER_TABLE_FILE, users);
        return token;
    }

    public static UserModel getUser(String token) {
        try {
            return ((UserTableModel) readJson(Constants.USER_TABLE_FILE, UserTableModel.class)).getUsers().get(token);
        } catch (Exception e) {
            return null;
        }
    }

    public static BookCategoryModel getCategory(int id) {
        try {
            return ((BookCategoryTableModel) readJson(Constants.BOOK_CATEGORY_TABLE_FILE, BookCategoryTableModel.class)).getCategories().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public static BookGenreModel getGenre(int id) {
        try {
            return ((BookGenreTableModel) readJson(Constants.BOOK_GENRE_TABLE_FILE, BookGenreTableModel.class)).getGenres().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public static String login(LoginUserModel user) throws IOException {
        UserTableModel users = (UserTableModel) readJson(Constants.USER_TABLE_FILE, UserTableModel.class);
        for (Map.Entry<String, UserModel> entry : users.getUsers().entrySet()) {
            if (Objects.equals(entry.getValue().getUsername(), user.getUsername()) && Objects.equals(encrypt(user.getPassword(), Constants.SECRET_KEY), entry.getValue().getPassword())) {
                System.out.println(entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }

    public static int createBook(BookModel book) throws IOException {
        BookTableModel books = (BookTableModel) readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class);
        for (BookModel bookModel : books.getBooks()) {
            if (bookModel.getName().equals(book.getName())) {
                return -1;
            }
        }

        for (int category : book.getCategoryId()) {
            System.out.println(getCategory(category));
            if (getCategory(category) == null) {
                return -1;
            }
        }
        System.out.println(getGenre(book.getGenre()));

        if (getGenre(book.getGenre()) == null) {
            return -1;
        }
        int id = books.getBooks().size() + 1;
        books.getBooks().add(new BookModel(book.getName(), book.getUrlOfContent(), book.getAuthor(), book.getCoverSheet(), book.getCategoryId(), book.getPagesAmount(), book.getReleaseYear(), book.getGenre(), id));
        writeJson(Constants.BOOK_TABLE_FILE, books);
        return id;
    }

    public static int createBookCategory(BookCategoryModel category) throws IOException {
        BookCategoryTableModel categories = (BookCategoryTableModel) readJson(Constants.BOOK_CATEGORY_TABLE_FILE, BookCategoryTableModel.class);
        for (BookCategoryModel bookModel : categories.getCategories()) {
            if (bookModel.getName().equals(category.getName())) {
                return -1;
            }
        }

        int id = categories.getCategories().size() + 1;
        categories.getCategories().add(new BookCategoryModel(category.getName(), id));
        writeJson(Constants.BOOK_CATEGORY_TABLE_FILE, categories);
        return id;
    }

    public static int createBookGenre(BookGenreModel category) throws IOException {
        BookGenreTableModel categories = (BookGenreTableModel) readJson(Constants.BOOK_GENRE_TABLE_FILE, BookGenreTableModel.class);
        for (BookGenreModel bookModel : categories.getGenres()) {
            if (bookModel.getName().equals(category.getName())) {
                return -1;
            }
        }

        int id = categories.getGenres().size() + 1;
        categories.getGenres().add(new BookGenreModel(category.getName(), id));
        writeJson(Constants.BOOK_GENRE_TABLE_FILE, categories);
        return id;
    }
}
