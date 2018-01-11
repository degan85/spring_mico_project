package com.mico.project.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	
	// todo 프로퍼티로 경로 연결
	// todo 파일명 변환
	// todo DB 저장
	
    private final Path rootLocation = Paths.get("D:\\temp\\spring");
    private String addFileName = "123123";

//    @Autowired
//    public FileSystemStorageService(StorageProperties properties) {
//        this.rootLocation = Paths.get(properties.getLocation());
//    }

    private String changeFileName(MultipartFile file) {
    	String originalFilename = file.getOriginalFilename();
    	
    	String fileName = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
    	String extName = originalFilename.substring(originalFilename.lastIndexOf('.'));
    	
    	String IdAndDate = "_[id]123_[date]123_";
		
    	return fileName+IdAndDate+extName;
    }
    
    @Override
    public void store(MultipartFile file) {
        
    	
    	try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(changeFileName(file)));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
        	if(! Files.exists(rootLocation)) {
        		Files.createDirectory(rootLocation);
        	}
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}