package com.example.catalogservice.messagequeue;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka //카프카에서 사용할수있도록 어노테이션 추가
@Configuration //설정!
public class KafkaConsumerConfig {

    //접속할수있는 카프카 정보가 있는 객체 생성 <컨슈머>
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        Map<String,Object> properties=new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.18.0.101:9092"); //카프카 브로커아이디 지정
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"consumerGroupId"); //Consumer들을 kafak에 등록에 토픽에 메세지를 들고가는 놈들
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);//키의역직렬화 , 데이터 타입
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); //VALUE값 역직렬화

        return new DefaultKafkaConsumerFactory<>(properties);
    }
    //리스너를 등록
    @Bean
  public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory
                =new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory()); //설정정보가 있는 빈등록
        return kafkaListenerContainerFactory;
    }
}
