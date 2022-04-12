package com.example.catalogservice.messagequeue;


import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//리스너를 이용하여 데이터를 가져와서 데이터 베이스를 업데이트
@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private  final CatalogRepository catalogRepository;

    //토픽에 구독했기때문에 토픽에 쌓인메세지를 가져온다<컨슈머>
    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage){
        log.info("Kafka message : -->{}",kafkaMessage);
        //역직렬화 하여 사용한다
        Map<Object,Object> map=new HashMap<>();
        ObjectMapper mapper=new ObjectMapper();
        //문자열로 전달되지만 우리가 원하는 상태로 바꿈
        try{
    map= mapper.readValue(kafkaMessage,new TypeReference<Map<Object,Object>>(){});

        } catch (JsonProcessingException e) {  //jsonparsing 에러
            e.printStackTrace();
        }
        //해당 상품의 카타로그정보를 가져옴
        CatalogEntity catalogEntity = catalogRepository
                                    .findByProductId((String)map.get("productId"));

        if(catalogEntity==null){
            throw  new RuntimeException("해당상품은 존재하지않습니다");
        }
        // 수량을 변경해준다
        catalogEntity.setStock(catalogEntity.getStock()-(Integer) map.get("qty"));
        catalogRepository.save(catalogEntity);
    }
}
