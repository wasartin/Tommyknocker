package com.piframe.tommyknocker.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReceiverControllerTest {


    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `When sending a kill request, should then go to the next image`(){
        val initialStateImage = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)

        val result = testRestTemplate.postForEntity("/v1/receiver/end", "/Users/wsartin/dev/newImages/posters", String::class.java)
        val currentStateImage = testRestTemplate.getForEntity("/v1/display/images/current", String::class.java)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        assertNotEquals(initialStateImage, currentStateImage)
    }
}