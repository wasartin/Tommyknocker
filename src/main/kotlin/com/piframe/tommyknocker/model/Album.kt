package com.piframe.tommyknocker.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.io.File
import java.util.*

@Component
class Album() {
    private val shuffledDirectory = ArrayList<String>()
    private var index = 0

    // Will need to use at some point
//    @Autowired
//    private val env: Environment? = null

    private var directory: String? = null

    // Debugging memory issue
    private var fullCycle = 1

    init {

        println("Album")

        val currentDirectory = File(directory?: "/Users/wsartin/dev/workshop/photo-db/posters/jpg")
        val files = currentDirectory.listFiles()
        val filesNames:MutableList<String> = mutableListOf()
        files?.map{
            if(validImage(it)) {
                filesNames.add(it!!.absolutePath)
            }
        }
        shuffledDirectory.addAll(filesNames)
        shuffledDirectory.shuffle()
    }

    private fun validImage(filename: File): Boolean {
        val extension = filename.name.lowercase(Locale.getDefault())
        return extension.endsWith(".jpg") || extension.endsWith(".jpeg") || extension.endsWith(".png")
    }

    operator fun next(): String {
        if (index >= shuffledDirectory.size) {
            shuffledDirectory.shuffle()
            index = 0
            println("Full cycles: ${fullCycle}, reshuffling images")
            fullCycle++
        }
        return shuffledDirectory[index++]
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
        return shuffledDirectory[index]
    }

    fun getList() = shuffledDirectory
}