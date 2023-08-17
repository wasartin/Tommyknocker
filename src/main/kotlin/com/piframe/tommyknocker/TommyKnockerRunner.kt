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

    // Other way
//    try{
//        val process: Process = processBuilder.start()
//        val reader = BufferedReader(InputStreamReader(process.inputStream))
//        val line = reader.readText();
//        val exitVal = process.waitFor()
//        if (exitVal == 0) {
//            println("Success!")
//            exitProcess(0)
//        } else {
//            println("Something bad")
//            println(line)
//            exitProcess(0)
//        }
//    } catch(e: Exception) {
//        println("Something went wrong")
//        println(e)
//    }


//    val GRADLE_INSTALLATION = "/usr/local/Cellar/gradle/8.3"
//
//    val GRADLE_PROJECT_DIRECTORY = "~/dev/workshop/display/";
//    val GRADLE_TASK = "run";
//
//    val gradleConn = GradleConnector.newConnector();
//    gradleConn.useInstallation(File(GRADLE_INSTALLATION));
//    gradleConn.forProjectDirectory(File(GRADLE_PROJECT_DIRECTORY));
//
//    val connection: ProjectConnection = gradleConn.connect()
//    val build = connection.newBuild()
//    build.forTasks(GRADLE_TASK)
//
//    println("About to start Gradle")
//    try{
//        build.run()
//        connection.close()
//    } catch(e: Exception) {
//        println("Well shoot")
//        println(e)
//    }
}