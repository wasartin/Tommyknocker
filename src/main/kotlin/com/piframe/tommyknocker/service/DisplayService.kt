package com.piframe.tommyknocker.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DisplayService(@Autowired val runner: RunnerService) {

    fun getListFromPhotoAlbum(): List<String> {
        return runner.imageList()
    }

}