package com.example.catalogservice.controller;


import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/catalog-service")
@RequiredArgsConstructor
@RestController
public class CatalogController {
     private final Environment env;
     private final CatalogService catalogService;


    @GetMapping("/health_check")
    public  String status(){

  return String.format("it is Working in Catalog Service Port %s"
          ,env.getProperty("local.server.port"));

    }
    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getAllUsers(){
        List<CatalogDto>  userList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result=new ArrayList<>();
        userList.forEach(v->{
            result.add(new ModelMapper().map(v,ResponseCatalog.class));
        });
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
