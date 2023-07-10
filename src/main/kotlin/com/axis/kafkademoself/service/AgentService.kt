package com.axis.kafkademoself.service

import com.axis.kafkademoself.model.Agent
import com.axis.kafkademoself.repository.AgentRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AgentService(private val agentRepository: AgentRepository){
    fun getAllAgents():Flux<Agent>{
        return agentRepository.findAll()
    }

    fun addAgent(agent: Agent):Mono<Agent>{
        return agentRepository.save(agent)
    }

    fun updateAgent(id:String,updatedAgent: Agent):Mono<Agent>{
        return agentRepository.findById(id)
            .flatMap {
                existingAgent ->
                val mergeAgent = existingAgent.copy(
                    fullName = updatedAgent.fullName,
                    age = updatedAgent.age,
                    gender = updatedAgent.gender
                )
                agentRepository.save(mergeAgent)
            }
    }

    fun getAgentById(id: String):Mono<Agent>{
        return agentRepository.findById(id)
    }

    fun deleteAgentById(id: String):Mono<Void>{
        return agentRepository.deleteById(id)
    }
}