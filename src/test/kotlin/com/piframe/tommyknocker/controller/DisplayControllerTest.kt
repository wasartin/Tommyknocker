package com.piframe.tommyknocker.controller

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
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
}