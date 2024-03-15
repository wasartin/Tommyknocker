package com.piframe.tommyknocker.controller

import com.piframe.tommyknocker.service.DisplayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Endpoint for runner to communicate back to the tommy knocker. s
 */
@RestController
@RequestMapping("/v1/receivers") // ToDo: rename to something like /screens
class ReceiverController (
    @Autowired private val displayService: DisplayService
) {

    /**
     * The display program has ended and is letting the Runner know
     */
    @PostMapping("/end")
    fun currentScreenIsEnding() {
        // ToDo: Loggings
        displayService.next()
    }
}
