package delectable.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import delectable.patchhelper.PATCH;

@Path("/order")
public class OrderService {
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOrdersByDate(@QueryParam("date") String x)
   {
	   boolean noparam = false;
	   if(x == null){
		   noparam = true;
	   }
	   if(!noparam)
		   return Response.status(200).entity("The getoordersbydate"
		   		+ " was called - GET orders by date " + x).build();
	   else
		   return Response.status(200).entity("The getoordersbydate"
		   		+ " was called - GET no param passed").build();
   }
   
   @GET
   @Path("/{oid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOrdersById(@PathParam("oid") int id)
   {
	   return Response.status(200).entity("The getorderbyid" +
		   		" was called - GET sample order id " + id + " , the called id " + id).build();
   }
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addOrder(InputStream incomingData) throws JsonParseException, JsonMappingException, IOException {
	   return Response.status(201).entity("The addorder" +
	   		" was called - POST, a sample id 24 ").build();
   }
   
   @PATCH
   @Path("/{oid}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response changeOrder(InputStream incomingData,@PathParam("oid") int id) throws JsonParseException, JsonMappingException, IOException {
	   return Response.status(201).entity("The changeorder" +
	   		" was called - PATCH,  " + id).build();
   }
   
   /*public List<User> getUsers(){
      return userDao.getAllUsers();
   }*/	
}