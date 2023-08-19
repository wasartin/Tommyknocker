package com.piframe.tommyknocker.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/v1/display/images")
class DisplayController {

    /**
     * Get a list of available image files to display
     */
    @GetMapping()
    fun getListOfAvailableImageFilesToDisplay(): ResponseEntity<String> {
        println("Listing current files of available images to display")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Receive a directory location of image files to cycle through
     */
    @PostMapping("/{directory}")
    fun setImageDirectory(@PathVariable directory: String): ResponseEntity<String>{
        println("Setting files to iterate to given image Directory")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Request specific image to display
     */
    @PutMapping("/{imageFileName}")
    fun setImageToDisplay(@PathVariable imageFileName: String): ResponseEntity<String>{
        println("Display image requested")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Request next image to display
     */
    @PutMapping("/next")
    fun nextImage(): ResponseEntity<String>{
        println("Going to the next image")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Request previous image to display
     */
    @PutMapping("/previous")
    fun previousImage(): ResponseEntity<String>{
        println("Going back to the previous image")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Request set time for display interval
     */
    @PutMapping("/interval/{intervalInMinutes}")
    fun setTimeForDisplayInterval(@PathVariable intervalInMinutes: Int): ResponseEntity<String>{
        println("Updating time for display interval")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }
}