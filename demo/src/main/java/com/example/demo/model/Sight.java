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
    private Long id;   // 自動生成 ID

    private String sightName;
    private String zone;
    private String category;
    private String photoURL;
    private String description;
    private String address;

    // 自訂 Constructor 只給 sightName 和 photoURL 用
    public Sight(String sightName, String photoURL) {
        this.sightName = sightName;
        this.photoURL = photoURL;
    }
}
