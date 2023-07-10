package com.axis.kafkademoself.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Agent")
data class Agent(
    @Id
    val id:String,
    val fullName:String,
    val age: String,
    val gender: String
)
