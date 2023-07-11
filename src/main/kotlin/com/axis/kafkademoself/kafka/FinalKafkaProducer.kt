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
import reactor.core.publisher.Flux
// Creating a new branch and just testing it
@Service
class FinalKafkaProducer(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String,Agent>,
    private val logger:Logger = LoggerFactory.getLogger(FinalKafkaProducer::class.java)
) {

    // For Posting Agent
    fun postAgent(agent: Agent){
        logger.info(String.format("Agent Data Sent ==> $agent"))

        var msg:Message<Agent> = MessageBuilder.withPayload(agent)
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Getting All Agents
    fun findAllAgents(){
        logger.info(String.format("Getting All Agents..."))
    }

    // For Getting Agent By Id
    fun getAgentById(id: String){
        logger.info(String.format("Getting Agent with ID:$id"))

        var msg:Message<String> = MessageBuilder.withPayload(id)
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Updating Agent
    fun updatedAgent(agent: Agent){
        logger.info(String.format("Updated Agent Details -> $agent"))

        var msg:Message<Agent> = MessageBuilder.withPayload(agent)
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

    // For Deleting Agent
    fun deleteAgent(id:String){
        logger.info(String.format("Deleting agent with ID:$id ..."))

        var msg:Message<String> = MessageBuilder.withPayload(id)
            .setHeader(KafkaHeaders.TOPIC,"final-topic1")
            .build()

        kafkaTemplate.send(msg)
    }

}