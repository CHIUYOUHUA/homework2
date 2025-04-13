package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private String sightName;   // 景點名稱
    private String zone;        // 所在區域
    private String category;    // 分類
    private String photoUrl;    // 圖片網址
    private String description; // 描述
    private String address;     // 地址

    // 自訂 Constructor：只給 sightName 和 photoUrl
    public Sight(String sightName, String photoUrl) {
        this.sightName = sightName;
        this.photoUrl = photoUrl;
    }
}
