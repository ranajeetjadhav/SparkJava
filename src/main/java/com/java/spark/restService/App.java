package com.java.spark.restService;

import static spark.Spark.*;

import spark.Request;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        get("/hello", (request, response)->"Hello World");
        
        get("/hello/:name", (req,res)->{
        	return "hello "+req.params(":name");
        });
    }
}
