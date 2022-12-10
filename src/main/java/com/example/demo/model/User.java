package com.example.demo.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.UUID;
import com.example.demo.validators.Patterns;
public abstract class User {
    @NotEmpty(message = "Name cannot be empty")
    public String name;
    @Pattern(regexp = Patterns.PHONE_NUMBER_PATTERN, message = "Phone must be of the form 01(3~9)-xxxx-xxxx")
    public String phone;
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    public String email;
    @Pattern(regexp = Patterns.UUID_PATTERN, message = "ID is not valid")
    public UUID id;
    public boolean active;
    public String password;

    public User( String name,String phone,String email,UUID id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.active=true;
        setPassword(phone);
    }
    public User( String name,String phone,String email,UUID id,String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.active=true;
        this.password=password;
    }
    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(10, new SecureRandom());
        this.password= bCryptPasswordEncoder.encode(password);
    }
    public String getPassword() {
        return this.password;
    }
}
