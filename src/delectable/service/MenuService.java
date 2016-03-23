package delectable.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import delectable.dto.MenuIdDTO;
import delectable.dto.MenuItemDetailDTO;
import delectable.dto.MenuItemDTO;

@Path("/menu")
public class MenuService {
	
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   public Response getMenu() throws JsonProcessingException{
 
		   MenuItemDTO[] mn = new MenuItemDTO[2];
		   mn[0] = new MenuItemDTO(); 
		   mn[1] = new MenuItemDTO();
		   ObjectMapper mapper = new ObjectMapper();
		   String jsonInString = new String();
		   jsonInString = mapper.writeValueAsString(mn);
 
		   return Response.status(200).entity(jsonInString).build();
	   }
	   
	   @GET
	   @Path("/{mid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public Response getMenuItem(@PathParam("mid") int id) throws JsonProcessingException{
 
		   MenuItemDetailDTO mn = new MenuItemDetailDTO();
		   mn.id = id;
		   ObjectMapper mapper = new ObjectMapper();
		   String jsonInString = new String();
		   jsonInString = mapper.writeValueAsString(mn);
 
		   return Response.status(200).entity(jsonInString).build();
	   }
	   
	   @POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addItem(InputStream incomingData) throws JsonParseException, JsonMappingException, IOException {
		   
		   ObjectMapper mapper = new ObjectMapper();
		   
			StringBuilder jsonInString = new StringBuilder();
			/*try {
				BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
				String line = null;
				while ((line = in.readLine()) != null) {
					jsonInString.append(line);
				}
			} catch (Exception e) {
				System.out.println("Error Parsing: - ");
			}*/
			jsonInString = new StringBuilder();
			jsonInString.append("{\"abc\":22,\"id\":5,\"name\":null,\"price_per_person\":0,\"minimum_order\":0,\"categories\":null}");
			System.out.println("Data Received: " + jsonInString.toString());
			MenuItemDTO myObjects = new MenuItemDTO(); 
			myObjects = mapper.readValue(jsonInString.toString(), MenuItemDTO.class);
			//call to add the item and then return the id
			//int id;
			MenuIdDTO myID = new MenuIdDTO();
			myID.id = 21;
			//mapper.readValue(MenuIdInterface, MenuIdInterface.class);
			String jsonOutIdString = new String();
			jsonOutIdString = mapper.writeValueAsString(myID);
			return Response.status(201).entity(jsonOutIdString).build();
		}
	 
	   
	   @PUT
	   @Path("/{mid}")
	   @Produces(MediaType.APPLICATION_JSON)
		public Response changeItem(InputStream incomingData) throws JsonParseException, JsonMappingException, IOException {
		   	//change the item
		   	ObjectMapper mapper = new ObjectMapper();
		   
			//StringBuilder jsonOutIdString = new StringBuilder();
			MenuItemDTO mn = new MenuItemDTO();
			MenuIdDTO myID = new MenuIdDTO();
			myID.id = 21;
			//mapper.readValue(MenuIdInterface, MenuIdInterface.class);
			String jsonOutIdString = new String();
			jsonOutIdString = mapper.writeValueAsString(myID);
			return Response.status(201).entity(jsonOutIdString).build();
	   }
}

