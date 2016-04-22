package delectable.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
import org.apache.*;
import org.apache.log4j.Logger;
*/
import delectable.dto.AdminSchrDTO;
import delectable.dto.ErrorDTO;
import delectable.dto.IdDTO;
import delectable.dto.MenuItemDTO;
import delectable.dto.MenuItemDetailDTO;
import delectable.dto.MenuItemIdPriceDTO;
import delectable.logic.MenuManager;
import delectable.logic.OrderManager;
import delectable.patchhelper.PATCH;

@Path("/admin")
public class AdminService {

   @PUT
   @Path("/menu")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addItem(InputStream incomingData,  @Context UriInfo uriInfo) 
		   throws JsonParseException, JsonMappingException, IOException, IllegalAccessException, InvocationTargetException {
	   
	   ObjectMapper mapper = new ObjectMapper();
	   MenuItemDTO mi = new MenuItemDTO();
	   
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
	   mi = mapper.readValue(jsonInString.toString(), MenuItemDTO.class);
	   IdDTO miID = MenuManager.menu.AddItem(mi);
	   
	   String jsonOutIdString = new String();
	   jsonOutIdString = mapper.writeValueAsString(miID);
	   UriBuilder builder = uriInfo.getAbsolutePathBuilder();
       builder.path(Integer.toString(miID.getId()));
	   return Response.created(builder.build()).entity(jsonOutIdString).build();
   }

   	
   @POST
   @Path("menu/{aid}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response changePrice(@PathParam("aid") int id, InputStream incomingData,  @Context UriInfo uriInfo) {
		  
	   ObjectMapper mapper = new ObjectMapper();
	   MenuItemIdPriceDTO mi = new MenuItemIdPriceDTO();
	   
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
	   try{
	   mi = mapper.readValue(jsonInString.toString(), MenuItemIdPriceDTO.class);
	   } catch( Exception e)
	   {
		   return Response.status(400).entity("").build();
	   }
	   
	   //reading jason as a tree and then traversing
	   JsonNode node = null ;
	   try {
		node = mapper.readTree(jsonInString.toString());
	   } catch (JsonProcessingException e) {
		e.printStackTrace();
	   } catch (IOException e) {
		e.printStackTrace();
	   }
	   if(node.get("price_per_person") == null || node.get("id") == null || node.size() > 2){
		   return Response.status(400).entity("").build();    
	   }
	   
	   try {
		   MenuManager.menu.ChangePrice(mi);
	   } catch (ArrayIndexOutOfBoundsException i)
	   {
		   return Response.status(400).entity("").build();
	   }
	   
	   UriBuilder builder = uriInfo.getAbsolutePathBuilder();
       builder.path(Integer.toString(mi.getId()));
	   return Response.created(builder.build()).build();
   }
   

   @GET
   @Path("/surcharge")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getSurcharge() throws JsonProcessingException
   {
	   AdminSchrDTO sch = MenuManager.menu.getSurcharge();
	   ObjectMapper mapper = new ObjectMapper();
	   String jsonInString = new String();
	   jsonInString = mapper.writeValueAsString(sch);
	   return Response.status(200).entity(jsonInString).build();
   }
   
   @POST
   @Path("/surcharge")
   @Produces(MediaType.APPLICATION_JSON)
   public Response setSurcharge( InputStream incomingData,  @Context UriInfo uriInfo)
   {
	   
	   ObjectMapper mapper = new ObjectMapper();
	   AdminSchrDTO scr = new AdminSchrDTO();
	   
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
	   try{
	   scr = mapper.readValue(jsonInString.toString(), AdminSchrDTO.class);
	   } catch( Exception e)
	   {
		   return Response.status(400).entity("").build();
	   }
	   
	   //reading json as a tree and then traversing
	   JsonNode node = null ;
	   try {
		node = mapper.readTree(jsonInString.toString());
	   } catch (JsonProcessingException e) {
		e.printStackTrace();
	   } catch (IOException e) {
		e.printStackTrace();
	   }
	   if(node.get("surcharge") == null || node.size() > 1){
		   return Response.status(400).entity("").build();    
	   }
	   
	   try {
		   MenuManager.menu.setSurcharge(scr);
	   } catch (IndexOutOfBoundsException i)
	   {
		   return Response.status(400).entity("").build();
	   }
	   
	   UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	   System.out.println(uriInfo.getBaseUri());
	   //System.out.println(uriInfo.get);
	   //System.out.println(uriInfo.getBaseUri());
	   
	   return Response.created(builder.build()).build();
   }
   
   @POST
   @Path("delivery/{oid}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response changeDelstatus(@PathParam("oid") int id, InputStream incomingData,  @Context UriInfo uriInfo) throws Exception {
		  
	   ObjectMapper mapper = new ObjectMapper();
	   IdDTO orderId = new IdDTO();
	   
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
	   try{
		   orderId = mapper.readValue(jsonInString.toString(), IdDTO.class);
	   } catch( Exception e)
	   {
		   return Response.status(400).entity("").build();
	   }
	   
	   //reading json as a tree and then traversing
	   JsonNode node = null ;
	   try {
		node = mapper.readTree(jsonInString.toString());
	   } catch (JsonProcessingException e) {
		e.printStackTrace();
	   } catch (IOException e) {
		e.printStackTrace();
	   }
	   if(node.get("id") == null || node.size() > 1 || node.get("id").asInt()!= orderId.getId()){
		   return Response.status(400).entity("").build();    
	   }
	   
	   try {
		   OrderManager.order.DeliverOrder(orderId);
	   } catch (ArrayIndexOutOfBoundsException i)
	   {
		   ErrorDTO e = new ErrorDTO();
		   e.setError("The id does not exist");
		   mapper = new ObjectMapper();
		   String jsonOutString = new String();
		   jsonOutString = mapper.writeValueAsString(e);
		   return Response.status(400).entity(jsonOutString).build();
	   }
	   catch( IndexOutOfBoundsException i)
	   {
		   ErrorDTO e = new ErrorDTO();
		   e.setError("The id does not exist");
		   mapper = new ObjectMapper();
		   String jsonOutString = new String();
		   jsonOutString = mapper.writeValueAsString(e);
		   return Response.status(400).entity(jsonOutString).build();
	   }
	   
	   UriBuilder builder = uriInfo.getAbsolutePathBuilder();
       builder.path(Integer.toString(orderId.getId()));
	   return Response.created(builder.build()).build();
   }
   
}