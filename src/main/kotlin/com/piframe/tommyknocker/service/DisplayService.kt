package com.piframe.tommyknocker.service

import com.piframe.tommyknocker.model.Album
import com.piframe.tommyknocker.model.ImageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class DisplayService(
    @Autowired val runner: RunnerService,
    @Autowired val album: Album
    ) {

    private var currentImage: String = "loading"

    init { currentImage = album.next() }

    fun getListFromPhotoAlbum() = album.getList()

    fun setNewImageDirectory(newDirectory: String) = album.updateAlbum(newDirectory)

    fun setImageList(imagePaths: List<ImageRequest>) =  album.updateAlbum(imagePaths)

    fun getCurrentImageDisplayed() = currentImage

    // ToDo: This could probably just take the direction of next/prev
    //@Scheduled(cron = "0 0/15 * * * ?")   // 15 minutes
    @Scheduled(cron = "0 * * * * *")        // 1 minutes
    private fun startDisplay() {
        runner.run(currentImage)
        currentImage = album.next()
    }

    fun next(): String {
        currentImage = album.next()
        startDisplay()
        return currentImage
    }

    fun previous(): String {
        currentImage = album.previous()
        startDisplay()
        return currentImage
    }
}