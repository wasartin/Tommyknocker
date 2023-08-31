package com.piframe.tommyknocker.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlbumTest {

    @Test
    fun `Given an Album, when setting a new directory, then should update to a new album`(){
        val initialAlbum = Album()
        val originalContents = initialAlbum.getList()

        val input = "/Users/wsartin/dev/newImages/posters"
        initialAlbum.updateAlbum(input)
        val results = initialAlbum.getList()

        assertTrue(originalContents.size != results.size)
    }

    @Test
    fun `Given an Album, when requesting next image, then should shuffle images after a full cycle`(){
        val album = Album()
        val originalContents = album.getList().toString()

        for(i in 0 until album.getList().size + 1) {
            album.next()
        }

        val shuffledContents = album.getList().toString()
        assertTrue(originalContents != shuffledContents)
    }

    @Test
    fun `Given an Album, when requesting the previous image, then it should get the previous image without cycling`(){
        val album = Album()
        val lastImage = album.getList()[album.getList().size - 1] // silly, but here we are
        val lastImageFromPreviousCommand = album.previous()

        assertTrue(lastImage == lastImageFromPreviousCommand)
    }
}