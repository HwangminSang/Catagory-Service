package com.example.catalogservice.service;


import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements  CatalogService{
   private  final CatalogRepository catalogRepository;

    @Override
    public List<CatalogDto> getAllCatalogs() {
        List<CatalogEntity> catalogEntityList=(List<CatalogEntity>)catalogRepository.findAll();
       System.out.println(catalogEntityList);
        List<CatalogDto> catalogDtos=new ArrayList<>();

        catalogEntityList.forEach(v->{
            catalogDtos.add( new ModelMapper().map(v,CatalogDto.class));
        });

        System.out.println(catalogDtos);
        return catalogDtos;
    }
}
