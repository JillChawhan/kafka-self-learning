package com.axis.kafkademoself.kafka

import com.axis.kafkademoself.model.Agent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class JsonKafkaProducer(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String,Agent>,
    private val logger: Logger = LoggerFactory.getLogger(JsonKafkaProducer::class.java)
) {
    fun sendJsonMessage(agent: Agent){

        logger.info(String.format("JSON BODY SENT -> ${agent.toString() }"))

        var message:Message<Agent> = MessageBuilder.withPayload(agent)
            .setHeader(KafkaHeaders.TOPIC,"self-learning-json")
            .build()

        kafkaTemplate.send(message)
    }
}