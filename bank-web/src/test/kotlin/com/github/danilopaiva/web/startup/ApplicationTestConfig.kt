package com.github.danilopaiva.web.startup

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ApplicationConfig::class)
open class ApplicationTestConfig
