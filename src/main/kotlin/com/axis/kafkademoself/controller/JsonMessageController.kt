//package com.axis.kafkademoself.controller
//
//import com.axis.kafkademoself.config.KafkaTopicConfig
//import com.axis.kafkademoself.kafka.JsonKafkaProducer
//import com.axis.kafkademoself.model.Agent
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/api/v1/kafka")
//class JsonMessageController(
//    @Autowired
//    private val jsonKafkaProducer: JsonKafkaProducer,
//    private val kafkaTopicConfig: KafkaTopicConfig
//) {
//
//    @PostMapping("/json-publish")
//    fun publishJsonData(@RequestBody agent:Agent):ResponseEntity<String>{
//        val topicName = kafkaTopicConfig.jcNewTopic().name().toString()
//        jsonKafkaProducer.sendJsonMessage(agent)
//        return ResponseEntity.ok("Json Body Sent to $topicName")
//    }
//
//}