
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
		   cDto = CustomerManager.getCusMan().getAllCustomers();
		   String jsonOut;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOut = mapper.writeValueAsString(cDto);
		   return Response.status(200).entity(jsonOut).build();
	   }
	   else{
		   //email id, name , phone number
		   System.out.println("paramter passed");
		   List<CustomerDTO> cDto = new ArrayList<CustomerDTO>();
		   cDto = CustomerManager.getCusMan().getAllCustomersMatching(x);
		   String jsonOut;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOut = mapper.writeValueAsString(cDto);
		   return Response.status(200).entity(jsonOut).build();
	   }
   }
   
   @GET
   @Path("/{cid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getCustomerById(@PathParam("cid") int id) throws IllegalAccessException, InvocationTargetException, JsonProcessingException
   {
	   ObjectMapper mapper = new ObjectMapper();
	   String jsonOutString;
	   CustomerDetailDTO mn = new CustomerDetailDTO();
	   try{
		   mn = CustomerManager.getCusMan().getCustomer(id);
	   }
	   catch( IndexOutOfBoundsException i)
	   {
		   ErrorDTO e = new ErrorDTO();
		   e.setError("The id does not exist");
		   mapper = new ObjectMapper();
		   jsonOutString = new String();
		   jsonOutString = mapper.writeValueAsString(e);
		   return Response.status(400).entity(jsonOutString).build();
	   }
	   jsonOutString = mapper.writeValueAsString(mn);
	   return Response.status(200).entity(jsonOutString).build();
   }
   	
}