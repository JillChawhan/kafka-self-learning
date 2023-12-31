package com.axis.kafkademoself.kafka

import com.axis.kafkademoself.model.Agent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class FinalKafkaConsumer(
    private val logger: Logger = LoggerFactory.getLogger(FinalKafkaConsumer::class.java)
) {

    @KafkaListener(topics = ["final-topic1"], groupId = "myConsumerGrp")
    fun consumePostedAgent(agent: Agent){
        logger.info(String.format("---- PRODUCED MESSAGE IS CONSUMED ----\n"))
    }


}