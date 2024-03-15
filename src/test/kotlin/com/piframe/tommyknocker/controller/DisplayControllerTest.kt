package com.piframe.tommyknocker.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

//import org.springframework.transaction.annotation.Transactional

//@Transactional
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DisplayControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `When requesting the list of images, should return them`(){
        val result = testRestTemplate.getForEntity("/v1/display/images", String::class.java)
        val body = (result.body as String)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        assertTrue(body.isNotEmpty())
    }

    @Test
    fun `When requesting a new image directory, should set to new images`(){
        val getAllImageResultOriginal = testRestTemplate.getForEntity("/v1/display/images", String::class.java)
        val allImagesOriginal = (getAllImageResultOriginal.body as String)

        val result = testRestTemplate.postForEntity("/v1/display/images/directory", "/Users/wsartin/dev/newImages/posters", String::class.java)
        val updateDirectoryBodyResults = (result.body as String)

        val getAllImageResultUpdated = testRestTemplate.getForEntity("/v1/display/images", String::class.java)
        val allImagesUpdated = (getAllImageResultUpdated.body as String)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        assertTrue(updateDirectoryBodyResults.isNotEmpty())
        assertNotEquals(allImagesOriginal, allImagesUpdated)
    }

    @Test
    fun `Given the system is running, when requesting which image is being displayed, return current image `() {
        val getAllImageResultOriginal = testRestTemplate.getForEntity("/v1/display/images", String::class.java)
        val allImagesOriginal = (getAllImageResultOriginal.body as String)

        val result = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val contents = (result.body as String)

        assertTrue(contents.isNotBlank())
        assertTrue(allImagesOriginal.contains(contents))
    }

    @Test
    fun `Given the system is running, when requesting the next image to be displayed, should return the next image from current image endpoint`(){
        val originalImageResult = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val originalImage = (originalImageResult.body as String)

        val nextImageResult = testRestTemplate.getForEntity("/v1/display/images/next", String::class.java)
        val nextImage = (nextImageResult.body as String)

        val currentImageResult = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val currentImage = (currentImageResult.body as String)

        assertTrue(nextImage != originalImage)
        assertTrue(nextImage == currentImage)
    }

    @Test
    fun `Given the system is running, when requesting the previous image to be displayed, should return the previous image from current image endpoint`() {
        val originalImageResult = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val originalImage = (originalImageResult.body as String)

        val previousImageResult = testRestTemplate.getForEntity("/v1/display/images/previous", String::class.java)
        val previousImage = (previousImageResult.body as String)

        val currentImageResult = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val currentImage = (currentImageResult.body as String)

        assertTrue(previousImage != originalImage)
        assertTrue(previousImage == currentImage)
    }

    @Test
    fun `Given the Display endpoint, when requesting a specific image to display, should display the image`(){
        val getAllImagesResultOriginal = testRestTemplate.getForEntity("/v1/display/images", String::class.java)
        val allImages = (getAllImagesResultOriginal.body as String)

        val originalCurrentImageResult = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val originalCurrentImage = (originalCurrentImageResult.body as String)

        val oneOfTheImages = oneOfThem(allImages)

        val setImageResult = testRestTemplate.postForEntity("v1/display/images", oneOfTheImages, String::class.java)
        val imageSet = setImageResult.body

        val currentImageResult = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)
        val currentImage = (currentImageResult.body as String)

        assertTrue(originalCurrentImage != imageSet)
        assertTrue(originalCurrentImage != currentImage)
        assertTrue(currentImage == imageSet)
    }

    // Mocked. Will need to refactor a json response back of the image objects
    private fun oneOfThem(input: String): String{
        return ""
    }
}