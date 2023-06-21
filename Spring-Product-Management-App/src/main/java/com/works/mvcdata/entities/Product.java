package com.works.mvcdata.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    private String title;
    private Integer price;
    private String detail;
    private Integer stock;
    private Long cid;
    @Lob
    private Blob image;
}
