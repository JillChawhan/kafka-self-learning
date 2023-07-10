package com.axis.kafkademoself.controller

import com.axis.kafkademoself.config.KafkaTopicConfig
import com.axis.kafkademoself.kafka.JsonKafkaProducer
import com.axis.kafkademoself.kafka.KafkaProducer
import com.axis.kafkademoself.model.Agent
import com.axis.kafkademoself.service.AgentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/kafka")
class KafkaMessageController (

    //constructor based dependency injection
    @Autowired
    private val kafkaProducer: KafkaProducer,
    private val jsonKafkaProducer: JsonKafkaProducer,
    private val agentService: AgentService,
    private val kafkaTopicConfig: KafkaTopicConfig
        ) {

    // http://localhost:6500/api/v1/kafka/publish?message=<Any message here>
    @GetMapping("/publish")
    fun publishMessage( @RequestParam("message") message:String ):ResponseEntity<String>{
        kafkaProducer.sendMessage(message)
        val topicName = kafkaTopicConfig.jcNewTopic().name().toString()
        return ResponseEntity.ok("Message sent successfully to topic {$topicName}")
    }

    @PostMapping("/json-publish")
    fun publishJsonData(@RequestBody agent: Agent):ResponseEntity<String>{
        val topicName = kafkaTopicConfig.jcNewTopic().name().toString()
        jsonKafkaProducer.sendJsonMessage(agent)
        return ResponseEntity.ok("Json Body Sent to $topicName")
    }

    @GetMapping("/get-all-agents")
    fun getAllAgents():Flux<Agent>{
        return agentService.getAllAgents()
    }

    @PostMapping("/add-agent")
    fun addAgent(@RequestBody agent: Agent):Mono<Agent>{
        return agentService.addAgent(agent)
    }

}