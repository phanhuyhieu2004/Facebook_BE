package com.example.facebook_be.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="visibility")
@Data
public class Visibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visibility_id;
    private String name;
//Chế độ của bài đăng: tạo sẵn dữ liệu trong DB là :Public,Private,....Các chế độ như trên fb


    public Visibility() {
    }

    public Visibility(Long visibility_id, String name) {
        this.visibility_id = visibility_id;
        this.name = name;
    }
}
