package com.piframe.tommyknocker.controller

import com.piframe.tommyknocker.service.DisplayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/v1/display/images") // ToDo: rename to something like /screens
class ScreenController(
    @Autowired private val displayService: DisplayService
) {

    /**
     * Get a list of available image files to display
     */
    @GetMapping()
    fun getListOfAvailableImageFilesToDisplay(): ResponseEntity<*> {
        val results = displayService.getListFromPhotoAlbum()
        return ResponseEntity.ok(results)
    }

    /**
     * Receive a directory location of image files to cycle through
     */
    @PostMapping("/directory")
    fun setImageDirectory(@RequestBody directory: String): ResponseEntity<String>{
        val success = displayService.setNewImageDirectory(directory)
        if(success){
            return ResponseEntity.ok(directory)
        }
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * TODO: Merge into getListOfAvailableImageFilesToDisplay() endpoint
     */
    @GetMapping("/current")
    fun getCurrentImageDisplayed(): ResponseEntity<String> {
        val result = displayService.getCurrentImageDisplayed()
        if(result.isNotBlank()){
            return ResponseEntity.ok(result)
        }
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Request next image to display
     */
    @GetMapping("/next")
    fun nextImage(): ResponseEntity<String>{
        val result = displayService.next()
        if(result.isNotBlank()){
            return ResponseEntity.ok(result)
        }
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    /**
     * Request previous image to display
     */
    @GetMapping("/previous")
    fun previousImage(): ResponseEntity<String>{
        val result = displayService.previous()
        if(result.isNotBlank()){
            return ResponseEntity.ok(result)
        }
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
     * Request set time for display interval
     */
    @PutMapping("/interval/{intervalInMinutes}")
    fun setTimeForDisplayInterval(@PathVariable intervalInMinutes: Int): ResponseEntity<String>{
        println("Updating time for display interval")
        return ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }
}