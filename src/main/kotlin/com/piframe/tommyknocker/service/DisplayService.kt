package com.piframe.tommyknocker.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
//class DisplayService(@Autowired val tommyKnockerRunner: TommyKnockerRunner) {
class DisplayService() {

    fun getListFromPhotoAlbum(): List<String> {
        return listOf()
        //return tommyKnockerRunner.photoAlbum?.getList() ?: listOf()
    }

}