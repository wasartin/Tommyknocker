package com.piframe.tommyknocker.service

import com.piframe.tommyknocker.model.Album
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

//Move the album into this
@Service
class DisplayService(
    @Autowired val runner: RunnerService,
    @Autowired val album: Album
    ) {

    fun getListFromPhotoAlbum(): List<String> {
        return album.getList()
    }

    fun setNewImageDirectory(newDirectory: String): Boolean {
        return album.updateAlbum(newDirectory)
    }

    //@Scheduled(cron = "0 0/15 * * * ?")   // 15
    @Scheduled(cron = "0 * * * * *")        // 1
    private fun startDisplay() {
        runner.run(album.next())
    }

}