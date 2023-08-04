package com.piframe.tommyknocker

import com.piframe.tommyknocker.model.Album
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalTime

@Service
class TommyKnockerRunner(
    @Autowired private var photoAlbum: Album
) {

    var displayCurrentPoster = true

    @PostConstruct
    fun main(args: Array<String>) {
        println("Startup")
        //var directoryFilePath = "/Users/wsartin/dev/workshop/piframe-display/resrc/jpgPosters"
        //var directoryFilePath = "/home/piframe/dev/photo-db/posters/jpg/"
        var directoryFilePath = "/Users/wsartin/dev/workshop/photo-db/posters/jpg"
        var intervalInMinutes = 15
        if (args.isNotEmpty()) {
            directoryFilePath = args[0]
            intervalInMinutes = args[1].toInt()
            println("New intervalInMinutes: $intervalInMinutes")
        }
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
        //TODO
        println("SHOWING POSTER ${image.absolutePath}")
    }
}