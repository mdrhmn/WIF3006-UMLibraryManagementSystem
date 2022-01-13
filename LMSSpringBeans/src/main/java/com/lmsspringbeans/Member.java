package com.lmsspringbeans;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;

public class Member extends User {

    public Member() {
    }

    public Member(String name, String username, String password, String role) {
        super(name, username, password, role);
    }
}
