package com.axis.kafkademoself.controller

import com.axis.kafkademoself.config.KafkaTopicConfig
import com.axis.kafkademoself.kafka.FinalKafkaProducer
import com.axis.kafkademoself.kafka.JsonKafkaProducer
import com.axis.kafkademoself.kafka.KafkaProducer
import com.axis.kafkademoself.model.Agent
import com.axis.kafkademoself.service.AgentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

@RestController
@RequestMapping("/api/v1/kafka")
class KafkaMessageController (

    //constructor based dependency injection
    @Autowired
    private val kafkaProducer: KafkaProducer,
    private val finalKafkaProducer: FinalKafkaProducer,
    private val jsonKafkaProducer: JsonKafkaProducer,
    private val agentService: AgentService,
    private val kafkaTopicConfig: KafkaTopicConfig
        ) {
    val jsonTopicName = kafkaTopicConfig.jcNewTopic().name().toString()
    // http://localhost:6500/api/v1/kafka/publish?message=<Any message here>
    @GetMapping("/publish")
    fun publishMessage( @RequestParam("message") message:String ):ResponseEntity<String>{
        kafkaProducer.sendMessage(message)
        val topicName = kafkaTopicConfig.jcNewTopic().name().toString()
        return ResponseEntity.ok("Message sent successfully to topic {$topicName}")
    }

    @PostMapping("/json-publish")
    fun publishJsonData(@RequestBody agent: Agent):ResponseEntity<String>{
        jsonKafkaProducer.sendJsonMessage(agent)
        return ResponseEntity.ok("Json Body Sent to $jsonTopicName")
    }

    @GetMapping("/get-all-agents")
    fun getAllAgents():Flux<Agent>{
        finalKafkaProducer.findAllAgents()
        return agentService.getAllAgents()
    }

    @PostMapping("/add-agent")
    fun addAgent(@RequestBody agent: Agent):Mono<Agent>{
        finalKafkaProducer.postAgent(agent)
        println("Agent with body -> $agent | sent to topic | sent to DB")
        return agentService.addAgent(agent)
    }

    @GetMapping("/agent-id/{id}")
    fun getAgentById(@PathVariable id:String):Mono<Agent>{
        finalKafkaProducer.getAgentById(id)
        return agentService.getAgentById(id)
    }

    @PutMapping("/update-agent/{id}")
    fun updateAgent(@PathVariable id: String, @RequestBody agent: Agent):Mono<Agent>{
        finalKafkaProducer.updatedAgent(agent)
        println("Agent updated with id -> $id | with body $agent")
        return agentService.updateAgent(id,agent)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteAgent(id: String):Mono<Void>{
        finalKafkaProducer.deleteAgent("Agent with id -> $id, deleted successfully.")
        return agentService.deleteAgentById(id)
    }

}