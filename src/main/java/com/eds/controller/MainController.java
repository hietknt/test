package com.eds.controller;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
@Path("/")
public class MainController {

    @GET
    @Produces({MediaType.TEXT_HTML})
    public FileInputStream getTest(){
        FileInputStream stream = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            stream = new FileInputStream(classLoader.getResource("templates/index.html").getFile());
        } catch (FileNotFoundException e) {
            //log.error("File not found exception. MainController.");
        }

        return stream;
    }

}
