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

@Path("/admin")
public class AdminService {


   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAdmins()
   {

	   return Response.status(200).entity("The getAdmins"
		   		+ " was called - GET").build();
   }
   
   @GET
   @Path("/{aid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAdminById(@PathParam("aid") int id)
   {
	   return Response.status(200).entity("The getAdminById" +
		   		" was called - GET sample report id " + id ).build();
   }
   	
   @POST
   @Path("/{aid}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addAdmin(@PathParam("aid") int id)
   {
	   return Response.status(201).entity("The addAdmin" +
	   		" was called - POST, a sample id 24 ").build();
   }
}