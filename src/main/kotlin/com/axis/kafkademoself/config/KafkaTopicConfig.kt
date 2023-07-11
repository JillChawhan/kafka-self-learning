package com.axis.kafkademoself.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
// responsible for creating topics
class KafkaTopicConfig {

    @Bean
    fun jcNewTopic():NewTopic{
        return TopicBuilder.name("self-learning")
            .partitions(2)
            .build()
    }

    @Bean
    fun jcNewTopic2():NewTopic{
        return TopicBuilder.name("self-learning-json")
            .partitions(2)
            .build()
    }

    @Bean
    fun finalTopic():NewTopic{
        return TopicBuilder.name("final-topic")
            .partitions(2)
            .build()
    }
}