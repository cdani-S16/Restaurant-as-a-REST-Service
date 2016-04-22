
package delectable.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import delectable.patchhelper.PATCH;
import delectable.dto.*;
import delectable.logic.CustomerManager;
import delectable.logic.MenuManager;

@Path("/customer")
public class CustomerService {
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getCustomersByKey(@QueryParam("key") String x) throws IllegalAccessException, InvocationTargetException, JsonProcessingException
   {
	   boolean noparam = false;
	   if(x == null){
		   noparam = true;
	   }
	   if(noparam){
		   List<CustomerDTO> cDto = new ArrayList<CustomerDTO>();
		   cDto = CustomerManager.cusMan.getAllCustomers();
		   String jsonOut;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOut = mapper.writeValueAsString(cDto);
		   return Response.status(200).entity(jsonOut).build();
		   //return Response.status(200).entity("The getCustomersByKey"
		   //		+ " was called - GET customer by key " + x).build();
	   }
	   else{
		   //email id, name , phone number
		   System.out.println("paramter passed");
		   List<CustomerDTO> cDto = new ArrayList<CustomerDTO>();
		   cDto = CustomerManager.cusMan.getAllCustomersMatching(x);
		   String jsonOut;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOut = mapper.writeValueAsString(cDto);
		   return Response.status(200).entity(jsonOut).build();
		
		   //return Response.status(200).entity("The getCustomersByKey"
		   //		+ " was called - GET no param passed").build();
	   }
   }
   
   @GET
   @Path("/{cid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getCustomerById(@PathParam("cid") int id) throws IllegalAccessException, InvocationTargetException, JsonProcessingException
   {
	   CustomerDetailDTO mn = new CustomerDetailDTO();
	   mn = CustomerManager.cusMan.getCustomer(id);
	   ObjectMapper mapper = new ObjectMapper();
	   String jsonOutString = mapper.writeValueAsString(mn);

	   return Response.status(200).entity(jsonOutString).build();
	   //return Response.status(200).entity("The getCustomerById" +
		//   		" was called - GET sample customer id " + id + " , the called id " + id).build();
   }
   	
}