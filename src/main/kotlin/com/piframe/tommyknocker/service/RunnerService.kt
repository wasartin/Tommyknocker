package com.piframe.tommyknocker.service

import com.piframe.tommyknocker.model.Album
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.File

@Service
class RunnerService(@Autowired private val photoAlbum: Album) {

    // 15 minutes
    //@Scheduled(cron = "0 0/15 * * * ?")
    @Scheduled(cron = "0 * * * * *")
    fun run() {
        killCurrentDisplay()
        try {
            val currentImage = File(photoAlbum.next())
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

    fun imageList(): List<String>{
        return photoAlbum.getList()
    }

    /**
     * Call Kotlin Display program
     */
    fun showPoster(image: File) {
        val command = "../display/gradlew -p ../display run"
        try {
            Runtime.getRuntime().exec(command)
        } catch (e: Exception){
            println("Oh Shoot")
            println(e)
        }
    }

}