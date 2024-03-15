package com.piframe.tommyknocker.service

import com.piframe.tommyknocker.model.Album
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.File

@Service
class RunnerService(@Value("\${debug}") private val debugMode: Boolean) {

    fun run(fileToDisplay: String) {
        killCurrentDisplay()
        try {
            val currentImage = File(fileToDisplay)
            showPoster(currentImage)
        } catch (e: Exception) {
            println("Error running ")
            println(e)
        }
    }

    private fun killCurrentDisplay(){
        // check if display running to on
        // send signal to kill
        println("Mock kill current display")
    }

    /**
     * Call Kotlin Display program
     */
    fun showPoster(image: File) {
        println("Showing poster -> ${image.absolutePath}")
        if(!debugMode){
            val command = "../display/gradlew -p ../display run"
            try {
                Runtime.getRuntime().exec(command)
            } catch (e: Exception){
                println("Failure in running display program")
                println(e)
            }
        }

    }

}