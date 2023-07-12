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
class FinalKafkaProducer(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String,Agent>,
    private val logger:Logger = LoggerFactory.getLogger(FinalKafkaProducer::class.java)
) {

    // For Posting Agent
    fun postAgent(agent: Agent){
        logger.info(String.format("Adding Agent Data ==> $agent"))

        var msg:Message<String> = MessageBuilder.withPayload("Added new Agent -> ${agent.toString()}")
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Getting All Agents
    fun findAllAgents(){
        logger.info(String.format("Getting All Agents..."))

        var msg:Message<String> = MessageBuilder.withPayload("Found All Agents...")
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Getting Agent By ID
    fun getAgentById(id: String){
        logger.info(String.format("Finding Agent with ID:$id"))

        var msg:Message<String> = MessageBuilder.withPayload("Found Agent with ID: $id")
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Updating Agent
    fun updatedAgent(id: String,agent: Agent){
        logger.info(String.format("Updated Agent Details (Agent ID: $id)-> $agent"))

        var msg:Message<String> = MessageBuilder.withPayload("Updated Agent (ID:$id) -> ${agent.toString()}")
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Deleting Agent
    fun deleteAgent(id:String){
        logger.info(String.format("Deleting agent with ID:$id ..."))

        var msg:Message<String> = MessageBuilder.withPayload("Deleted Agent with ID: $id")
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

}