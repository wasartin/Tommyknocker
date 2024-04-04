package com.piframe.tommyknocker.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlbumTest {

    @Test
    fun `Given an Album, when setting a new directory, then should update to a new album`(){
        val initialAlbum = Album(true)
        val originalContents = initialAlbum.getList()

        val input = "/Users/wsartin/dev/newImages/posters"
        initialAlbum.updateAlbum(input)
        val results = initialAlbum.getList()

        assertTrue(originalContents.size != results.size)
    }

    @Test
    fun `Given a list of files, when setting a new album, then should create album from list`(){
        var firstImage = ImageRequest()
        firstImage.name = "Battle"
        firstImage.filePath = "/Users/wsartin/dev/workshop/tommyknocker/src/main/resources/imgs/battleOfJutlan1916.jpeg"
        var secondImage = ImageRequest()
        secondImage.name = "Other"
        secondImage.filePath = "/Users/wsartin/dev/workshop/tommyknocker/src/main/resources/imgs/iSpy.png"

        val initialAlbum = Album(true)

        var result = initialAlbum.updateAlbum(listOf(firstImage, secondImage))
        assertTrue(result == 2)
    }

    @Test
    fun `Given an Album, when requesting next image, then should shuffle images after a full cycle`(){
        val album = Album(true)
        val originalContents = album.getList().toString()

        for(i in 0 until album.getList().size + 1) {
            album.next()
        }

        val shuffledContents = album.getList().toString()
        assertTrue(originalContents != shuffledContents)
    }

    @Test
    fun `Given an Album, when requesting the previous image, then it should get the previous image without cycling`(){
        val album = Album(true)
        val lastImage = album.getList()[album.getList().size - 1] // silly, but here we are
        val lastImageFromPreviousCommand = album.previous()

        assertTrue(lastImage == lastImageFromPreviousCommand)
    }
}