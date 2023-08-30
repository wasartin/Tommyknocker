package com.piframe.tommyknocker.model

import org.springframework.stereotype.Component
import java.io.File
import java.util.*

@Component
class Album {
    private var shuffledDirectory = ArrayList<String>()
    private var index = 0

    // Debugging memory issue
    private var fullCycle = 1

    init {
        updateAlbum("/Users/wsartin/dev/workshop/photo-db/posters/jpg") //TODO: Update to env var
    }

    fun updateAlbum(directory: String){
        val currentDirectory = File(directory)
        val files = currentDirectory.listFiles()
        val filesNames:MutableList<String> = mutableListOf()
        files?.map{
            if(it.isValidImage()) {
                filesNames.add(it!!.absolutePath)
            }
        }
        shuffledDirectory = ArrayList<String>()
        shuffledDirectory.addAll(filesNames)
        shuffledDirectory.shuffle()
    }

    operator fun next(): String {
        if (index >= (shuffledDirectory.size)) {
            shuffledDirectory.shuffle()
            index = 0
            println("Full cycles: ${fullCycle}, reshuffling images")
            fullCycle++
        }
        return shuffledDirectory.get(index++) ?: ""
    }

    /**
     * TODO: Should this wrap around?
     * 0 -> shuffedDirectory.size - 1
     */
    fun previous(): String {
        index--
        if (index < 0) {
            index = 0
        }
        return shuffledDirectory.get(index) ?: ""
    }

    fun getList() = shuffledDirectory

    /**
     * Extension File function to ensure image has correct file extension.
     * (jpg, jpeg, png)
     */
    private fun File.isValidImage() : Boolean {
        val extension = this.name.lowercase(Locale.getDefault())
        return extension.endsWith(".jpg") || extension.endsWith(".jpeg") || extension.endsWith(".png")
    }

}