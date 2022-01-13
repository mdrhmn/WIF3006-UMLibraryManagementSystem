package com.lmsspringbeans;

import java.util.ArrayList;
import org.springframework.context.ApplicationContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author muhdrahiman
 */
public class User {

    int id;
    String name;
    String username;
    String password;
    String role;
    ApplicationContext context;

    public static int count = 0;
    public static ArrayList<User> userList = new ArrayList<>();

    public User() {
    }

    public void setApplicationContext(ApplicationContext context) {
        if (this.context == null) {
            System.err.println("MEMBER: CONTEXT PASSED");
            this.context = (ApplicationContext) context;

            userList.add(new Member("Ajwad Alias", "ajwadalias", "ajwad123", "Member"));
            userList.add(new Member("Ashraf Alias", "ashrafalias", "ashraf123", "Member"));
            userList.add(new Librarian("Muhd Rahiman", "mdrhmn", "ray123", "Librarian"));
        }
    }

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = count;
        count++;
    }

    public void initialise(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
