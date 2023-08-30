package com.piframe.tommyknocker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class TommyknockerApplication
fun main(args: Array<String>) {
	runApplication<TommyknockerApplication>(*args)
}
