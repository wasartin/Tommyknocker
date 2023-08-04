package com.piframe.tommyknocker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class TommyknockerApplication

fun main(args: Array<String>) {
	runApplication<TommyknockerApplication>(*args)
}
