package com.axis.kafkademoself.kafka

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer (
    @Autowired
    // autowiring helps to create constructor as well for the particular class
    private val kafkaTemplate: KafkaTemplate<String,String>
        ) {

    // logger for logging the messages sent by producer
    val logger: Logger = LoggerFactory.getLogger(KafkaProducer::class.java)

    fun sendMessage(message:String){
        // using logger instance we can log the details using info method and String.format method to
        // print the logs as per our need
        logger.info(String.format("Sent message details: $message"))
        kafkaTemplate.send("self-learning",message)
    }
}