package com.revature.rentals.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String username;

    @JsonIgnore
    @Column(name = "password_hash")
    private byte[] passwordHash;
    @JsonIgnore
    private byte[] salt;

    public Customer(String username, String password) {
        this.username = username;
        this.passwordHash = hashPassword(password, salt = genSalt());
    }

    public Customer(String username, byte[] passwordHash, byte[] salt) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public Customer() {}

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other == null || getClass() != other.getClass()) { return false; }
        Customer customer = (Customer) other;
        return username.equals(customer.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + username + '\'' +
                '}';
    }

    public boolean correctPassword(String password) {
        return Arrays.equals(passwordHash, hashPassword(password, salt));
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public static boolean validName(String name) {
        return name.matches("^[\\p{L} .'-]+$");
    }

    public static boolean validPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");
    }

    public static byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            digest.update(salt);

            return digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] genSalt() {
        byte[] salt = new byte[32];

        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            ThreadLocalRandom.current().nextBytes(salt);
        }
        return salt;
    }
}