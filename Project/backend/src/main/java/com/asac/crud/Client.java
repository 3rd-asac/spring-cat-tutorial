package com.asac.crud;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
@Table
public class Client {


    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    @Id
    private Long index;
    private String id;
    private String nickName;
    private byte[] privateCode;
    private LocalDate createdAt;
    @Transient
    private int dayPassed;

    public Client() {
        this.createdAt = LocalDate.now();
    }

    public Client(String id, String nickName, String privateCode) {
        this.id = id;
        this.nickName = nickName;
        this.privateCode = encrypt(privateCode);
        this.createdAt = LocalDate.now();
    }

    public void setPrivateCode(String privateCode) {
        this.privateCode = encrypt(privateCode);
    }

    public int getDayPassed() {
        return Period.between(createdAt, LocalDate.now()).getDays();
    }

    byte[] encrypt(String plain) {
        try {
            MessageDigest a = MessageDigest.getInstance("SHA-256");
            return a.digest(plain.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ignored) {
        }
        return null;
    }


}