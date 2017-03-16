/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapiexample;

import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author x08424179
 */

@Path("/home")
public class HomeController {
    //Response
    
    //localhost:8080/api/home
    @GET
    @Produces(MediaType.TEXT_PLAIN)//use string OR MediaType
    public Response getHome(){
        HashMap map = new HashMap();
        map.put("name", "Benny");
        map.put("date", "16/3/2017");
        
        Gson json = new Gson();
        
        return Response.status(200).entity(json.toJson(map)).build();
    }
    
    //localhost:8080/api/home/query?name=benny&&date=16/03/2017
    @GET
    @Path("/query")
    @Produces(MediaType.TEXT_PLAIN)
    public Response showUserName(
            @QueryParam("name") String name,
            @QueryParam("date") String date){
        
        String result = "name: " + name + ", date: " + date;
        return Response.status(200).entity(result).build();
    }
    
    //localhost:8080/api/home
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("text/plain")
    public Response postDATA(@FormParam("name") String name,
            @FormParam("date") String date,
            @FormParam("time") String time){
        
        String result = "Name: " + name + " Date: " + date + " Time: " + time;
        return Response.status(200).entity("Data Sent from form: " + result).build();
    }
    
    //localhost:8080/api/home/calculator
    @POST
    @Path("/calculator")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response calculate(@FormParam("num1") String num1,
            @FormParam("num2") String num2){
        double n1 = Double.parseDouble(num1);
        double n2 = Double.parseDouble(num2);
        Calculator cal = new Calculator(n1, n2);
        Gson json = new Gson();
        
        return Response.status(200).entity(json.toJson(cal)).build();
    }
    
    //locahost:8080/api/home/path/benny
    @GET
    @Path("/path/{name}")
    @Produces("application/json")
    public Response getPathData(@PathParam("name") String name){
        Gson js = new Gson();
        String result = "{name: " + name+"}";
        return Response.status(200).entity(js.toJson(result)).build();
    }
            
    
    
}
