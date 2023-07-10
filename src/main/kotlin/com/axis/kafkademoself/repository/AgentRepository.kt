package com.axis.kafkademoself.repository

import com.axis.kafkademoself.model.Agent
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AgentRepository: ReactiveMongoRepository<Agent,String> {
}