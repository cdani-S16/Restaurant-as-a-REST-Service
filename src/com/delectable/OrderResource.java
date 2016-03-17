package com.delectable;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/orders")
public class OrderResource {

   //UserDao userDao = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.TEXT_PLAIN)
   public String getUsers(){
	   return "orderssss";
   }
   
   /*public List<User> getUsers(){
      return userDao.getAllUsers();
   }*/	
}