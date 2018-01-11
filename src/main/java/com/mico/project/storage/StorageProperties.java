package com.mico.project.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("classpath:filelocation.yml")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "D:\\temp";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
