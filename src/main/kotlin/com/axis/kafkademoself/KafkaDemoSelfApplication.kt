package com.axis.kafkademoself

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaDemoSelfApplication

fun main(args: Array<String>) {
	runApplication<KafkaDemoSelfApplication>(*args)
}
