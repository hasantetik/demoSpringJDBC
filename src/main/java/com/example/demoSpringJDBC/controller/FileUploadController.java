package com.example.demoSpringJDBC.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController @RequestMapping(path = "/file") public class FileUploadController
{
    // http://localhost:8080/file/postFile
    @PostMapping(path = "/postFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postFile(@RequestBody MultipartFile file)
    {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        try
        {
            Files.write(Paths.get("C:/SpringLogs/" + file.getOriginalFilename()), file.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // http://localhost:8080/file/postFile2
    @PostMapping(path = "/postFile2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postFile2(@RequestParam("file") MultipartFile file)
    {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        try
        {
            Files.write(Paths.get("C:/SpringLogs/" + file.getOriginalFilename()), file.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // http://localhost:8080/file/postStream
    @PostMapping(path = "/postStream", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void postStream(HttpServletRequest request)
    {
        try
        {
            InputStream str = request.getInputStream();
            Files.copy(str,Paths.get("C:/SpringLogs/someFile.pdf"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}