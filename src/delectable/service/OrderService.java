package delectable.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import delectable.dto.ErrorDTO;
import delectable.dto.IdDTO;
import delectable.dto.MenuItemDTO;
import delectable.dto.OrderAddedDTO;
import delectable.dto.OrderDTO;
import delectable.dto.OrderDetailDTO;
import delectable.dto.OrderMiniDTO;
import delectable.logic.CustomerManager;
import delectable.logic.MenuManager;
import delectable.logic.OrderManager;
import delectable.patchhelper.PATCH;

@Path("/order")
public class OrderService {
   
	public JsonNode ExtractJSONNODE(String jsonInString)
	{
		JsonNode jNode = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
		   jNode = mapper.readTree(jsonInString.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return jNode;
	}
	
	
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOrdersByDate(@QueryParam("date") String x) throws JsonProcessingException, IllegalAccessException, InvocationTargetException
   {
	   boolean noparam = false;
	   if(x == null){
		   noparam = true;
	   }
	   ObjectMapper mapper = new ObjectMapper();
	   String jsonInString = new String();
	   List<OrderMiniDTO> mi = OrderManager.getOrderMan().getAllOrders();
	   jsonInString = mapper.writeValueAsString(mi);
	   return Response.status(200).entity(jsonInString).build();
   }
   
   @GET
   @Path("/{oid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOrdersById(@PathParam("oid") int id) throws IllegalAccessException, InvocationTargetException, JsonProcessingException
   {
	   OrderDetailDTO dispOrder = new OrderDetailDTO();
	   	
	   ObjectMapper mapper = new ObjectMapper();
	   String jsonOutString = new String();
	   dispOrder = OrderManager.getOrderMan().getOrder(id);
	   jsonOutString = mapper.writeValueAsString(dispOrder);
	   return Response.status(200).entity(jsonOutString).build();
	   	
   }


   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addOrder(InputStream incomingData, @Context UriInfo uriInfo) throws Exception {
	   ObjectMapper mapper = new ObjectMapper();
	   OrderDTO order = new OrderDTO();
	   
	   StringBuilder jsonInString = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				jsonInString.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}

	   try{
		   order = mapper.readValue(jsonInString.toString(), OrderDTO.class);
	   } catch( Exception e)
	   {
		   return Response.status(400).entity("").build();
	   }

	   OrderAddedDTO oa;
	   try {
		   oa = OrderManager.getOrderMan().addOrder(order);
	   }  catch (IndexOutOfBoundsException i)
	   {
		   ErrorDTO e = new ErrorDTO();
		   e.setError("The menu id does not exist");
		   mapper = new ObjectMapper();
		   String jsonOutString = new String();
		   jsonOutString = mapper.writeValueAsString(e);
		   return Response.status(400).entity(jsonOutString).build();
	   }
	   catch( NumberFormatException e)
	   {
		   ErrorDTO err = new ErrorDTO();
		   err.setError(e.getMessage().toString());
		   mapper = new ObjectMapper();
		   String jsonOutString = new String();
		   jsonOutString = mapper.writeValueAsString(err);
		   return Response.status(400).entity(jsonOutString).build();
	   }
	   
	   UriBuilder builder = uriInfo.getAbsolutePathBuilder();
       builder.path("cancel/" + Integer.toString(oa.getId()));
       oa.setCancel_url(builder.build().toString());
	   String jsonOutString = new String();
	   jsonOutString = mapper.writeValueAsString(oa);
	   return Response.status(201).entity(jsonOutString).build();
   }
   
   @PATCH
   @Path("/{oid}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response changeOrder(InputStream incomingData,@PathParam("oid") int id) throws JsonParseException, JsonMappingException, IOException {
	   return Response.status(201).entity("The changeorder" +
	   		" was called - PATCH,  " + id).build();
   }
   
   
   @POST
   @Path("cancel/{oid}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response cancelOrder(InputStream incomingData,@PathParam("oid") int id) throws JsonParseException, JsonMappingException, IOException {
	   
	   IdDTO order = new IdDTO();
	   ObjectMapper mapper = new ObjectMapper();
	   StringBuilder jsonInString = new StringBuilder();
	   try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				jsonInString.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		 if(jsonInString.length() == 0)
			   return Response.noContent().entity("").build();
		   
		 //reading json as a tree and then traversing
		 JsonNode node = ExtractJSONNODE(jsonInString.toString());
		 
		 if(node.get("id") == null || node.size() > 1 
			   || node.get("id").asInt()!= id){
			 return Response.status(400).entity("").build();    
		 	}
		 try{
			 order = mapper.readValue(jsonInString.toString(), IdDTO.class);
		 	} catch( Exception e)
		 	{
		 		return Response.status(400).entity("").build();
		   }

	   try {
		   OrderManager.getOrderMan().CancelOrder(order);
	   } catch (IndexOutOfBoundsException i)
	   {
		   return Response.status(400).entity(" Enter a valid order id "
		   		+ "for cancellation").build();
	   }
	   catch (Exception e)
	   {
		   String jsonOutString;
		   ErrorDTO err = new ErrorDTO();
		   err.setError(e.getLocalizedMessage());
		   mapper = new ObjectMapper();
		   jsonOutString = mapper.writeValueAsString(err);
		   return Response.status(400).entity(jsonOutString).build();
	   }
	   
	   return Response.status(201).entity("").build();

   }
}