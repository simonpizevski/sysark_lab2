package com.example.sysark_lab2.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("")
public class HelloResource {
    @GET
    @Path("/hello-world")
    @Produces("text/plain")
    public String hello() {
        return "Hello, visit http://localhost:8080/sysark_lab2-1.0-SNAPSHOT/index.html for more functions";
    }
}