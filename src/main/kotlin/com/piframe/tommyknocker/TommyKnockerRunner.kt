package com.piframe.tommyknocker

import com.piframe.tommyknocker.model.Album
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalTime


@Service
class TommyKnockerRunner() {

    var photoAlbum: Album? = null
    var displayCurrentPoster = true

    init {
        println("Startup")
        //var directoryFilePath = "/Users/wsartin/dev/workshop/piframe-display/resrc/jpgPosters"
        //var directoryFilePath = "/home/piframe/dev/photo-db/posters/jpg/"
        var directoryFilePath = "/Users/wsartin/dev/workshop/photo-db/posters/jpg"
        var intervalInMinutes = 15
        // TODO: change to ENV Vars
//        if (args.isNotEmpty()) {
//            directoryFilePath = args[0]
//            intervalInMinutes = args[1].toInt()
//            println("New intervalInMinutes: $intervalInMinutes")
//        }
        photoAlbum = Album(directoryFilePath)
        run(intervalInMinutes)
    }

    private fun run(intervalInMinutes: Int) {
        try {
            while (true) {
                displayCurrentPoster = true
                val startTime = LocalTime.now()
                val endTime = startTime.plusMinutes(intervalInMinutes.toLong())
                val currentImage = File(photoAlbum!!.next())
                showPoster(currentImage)
                while (displayCurrentPoster) {
                    Thread.sleep(5)
                    if (LocalTime.now().isAfter(endTime)) {
                        displayCurrentPoster = false
                        // Hit endpoint to kill
                    }
                }
            }
        } catch (e: Exception) {
            println("Error running ")
            println(e)
        }
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