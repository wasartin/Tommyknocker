package com.piframe.tommyknocker.model
import com.fasterxml.jackson.annotation.JsonProperty

class ImageRequest {
    @JsonProperty("name")
    public var name: String = ""
    @JsonProperty("filePath")
    public var filePath: String = ""
}