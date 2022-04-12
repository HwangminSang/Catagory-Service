package com.example.catalogservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CatalogDto implements Serializable {


    private  String productId;
    private  Integer qty;
    private  Integer unitPrice;
    private  Integer totalPrice;
    private  String productName;
    private Integer stock;
    private  String orderId;
    private  String userId;
    private Date createdAt;
}
