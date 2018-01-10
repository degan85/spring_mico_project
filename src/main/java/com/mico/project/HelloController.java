package com.mico.project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller 
@RequestMapping("/hello") 
public class HelloController { 
	
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String hello() { return "hello"; } 

	
	@RequestMapping("/greeting") 
	public String greeting() { return "index"; }

	@RequestMapping(value = "/ajaxUpload")
    public String ajaxUpload() {
        return "ajaxUpload";
    }
     
    
    private static String UPLOADED_FOLDER = "D://temp//";
    

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
        @RequestParam("uploadfile") MultipartFile uploadfile) {
      
      try {
        // Get the filename and build the local file path
        String filename = uploadfile.getOriginalFilename();
        String directory = UPLOADED_FOLDER;
        String filepath = Paths.get(directory, filename).toString();
        
        // Save the file locally
        BufferedOutputStream stream =
            new BufferedOutputStream(new FileOutputStream(new File(filepath)));
        stream.write(uploadfile.getBytes());
        stream.close();
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      
      return new ResponseEntity<>(HttpStatus.OK);
    } // method uploadFile



} 

	


