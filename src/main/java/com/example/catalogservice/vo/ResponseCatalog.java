package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

//반환용 클라리언트에게
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //null값은 반환안함
public class ResponseCatalog {

    private  String productId;
    private  String productName;

    private  Integer unitPrice;
    private  Integer stock;
    private Date createdAt;

}
