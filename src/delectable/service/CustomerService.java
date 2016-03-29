
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

@Path("/customer")
public class CustomerService {
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getCustomersByKey(@QueryParam("key") String x)
   {
	   boolean noparam = false;
	   if(x == null){
		   noparam = true;
	   }
	   if(!noparam)
		   return Response.status(200).entity("The getCustomersByKey"
		   		+ " was called - GET customer by key " + x).build();
	   else
		   return Response.status(200).entity("The getCustomersByKey"
		   		+ " was called - GET no param passed").build();
   }
   
   @GET
   @Path("/{cid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getCustomerById(@PathParam("cid") int id)
   {
	   return Response.status(200).entity("The getCustomerById" +
		   		" was called - GET sample customer id " + id + " , the called id " + id).build();
   }
   	
}