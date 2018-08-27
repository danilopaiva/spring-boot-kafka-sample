package com.github.danilopaiva.kafka.startup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = ["com.github.danilopaiva"])
@EnableKafka
open class ApplicationConfig
