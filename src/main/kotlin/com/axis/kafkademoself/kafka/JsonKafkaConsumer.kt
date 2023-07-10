package com.axis.kafkademoself.kafka

import com.axis.kafkademoself.model.Agent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class JsonKafkaConsumer(
    private val logger: Logger = LoggerFactory.getLogger(JsonKafkaConsumer::class.java)
) {

    @KafkaListener(topics = ["self-learning-json"], groupId = "myConsumerGrp")
    fun consumeJsonMessage(agent: Agent){
        logger.info(String.format("JSON BODY RECEIVED -> ${agent.toString()}"))
    }
}