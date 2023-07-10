package com.axis.kafkademoself.kafka

import com.axis.kafkademoself.config.KafkaTopicConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer(
    // logger to be introduced inside function
    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)
){

    @KafkaListener(topics = ["self-learning"], groupId =  "myConsumerGrp")
    fun consumeMessage(message : String){
        logger.info(String.format("Received message details: $message"))
    }
}