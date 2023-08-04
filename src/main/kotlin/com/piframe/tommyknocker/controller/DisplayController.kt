package com.piframe.tommyknocker.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/v1/display")
class DisplayController {

    /**
     * Get a list of available image files to display
     */
    @GetMapping()
    fun getListOfAvailableImageFilesToDisplay(): ResponseEntity<String> {
        return ResponseEntity<String>(HttpStatus.ACCEPTED)
    }

    /**
     * Receive a directory location of image files to cycle through
     */

    /**
     * Request specific image to display
     */

    /**
     * Request next image to display
     */

    /**
     * Request previous image to display
     */

    /**
     * Request set time for display interval
     */
}