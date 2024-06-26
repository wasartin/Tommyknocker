package com.piframe.tommyknocker.model

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

@Component
class Album(@Value("\${debug}") private val debugMode: Boolean) {
    private var shuffledDirectory = ArrayList<String>()
    private var directoryOfImages = null
    private var index = 0

    // Debugging memory issues
    private var fullCycle = 1

    init {
        val directory = if(debugMode) "/Users/wsartin/dev/workshop/tommyknocker/src/main/resources/imgs" else "/home/piframe/dev/photo-db/posters/jpg/"
        updateAlbum(directory)
    }

    fun updateAlbum(directory: String): Boolean {
        val currentDirectory = File(directory)
        if(!currentDirectory.isDirectory) return false

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
        return true
    }

    fun updateAlbum(imageFiles: List<ImageRequest>): Int {
        val fileNames:MutableList<String> = mutableListOf()
        for(img in imageFiles){
            if(File(img.filePath).isValidImage()){
                fileNames.add(img.filePath);
            }
        }

        return updateShuffledImages(fileNames)
    }

    private fun updateShuffledImages(images: List<String>): Int{
        shuffledDirectory = ArrayList()
        shuffledDirectory.addAll(images)
        shuffledDirectory.shuffle()
        return shuffledDirectory.size
    }

    fun next(): String {
        if (index >= (shuffledDirectory.size)) {
            shuffledDirectory.shuffle()
            index = 0
            println("Full cycles: ${fullCycle}, reshuffling images")
            fullCycle++
        }
        return shuffledDirectory.get(index++)
    }

    /**
     * Call the previous image
     * Wraps around if at first Index
     */
    fun previous(): String {
        index--
        if (index < 0) {
            index = shuffledDirectory.size - 1
        }
        return shuffledDirectory[index]
    }

    /**
     * Return list of file locations of each image as a String
     */
    fun getList(): ArrayList<String> {
        return shuffledDirectory
    }

    /**
     * Extension File function to ensure image has correct file extension.
     * (jpg, jpeg, png)
     */
    private fun File.isValidImage() : Boolean {
        val extension = this.name.lowercase(Locale.getDefault())
        return extension.endsWith(".jpg") || extension.endsWith(".jpeg") || extension.endsWith(".png")
    }
}