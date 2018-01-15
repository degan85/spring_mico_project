package com.mico.project.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@ConfigurationProperties("classpath:filelocation.yml")

@Component
public class StorageProperties {

    /**
     * Folder location for storing files
     */
	@Value("${file}")
    private String location;
//    = "D:\\temp";

    public String getLocation() {
        return "aaa";
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
