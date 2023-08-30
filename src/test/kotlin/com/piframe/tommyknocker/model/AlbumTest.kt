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
}