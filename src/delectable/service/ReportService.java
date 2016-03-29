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

@Path("/report")
public class ReportService {

   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getReports()
   {

	   return Response.status(200).entity("The getReports"
		   		+ " was called - GET").build();
   }
   
   @GET
   @Path("/{rid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getReportsByIdByDateRange(@PathParam("rid") int id, 
		   @QueryParam("start_date") String startDate, 
		   @QueryParam("end_date") String endDate)
   {
	   return Response.status(200).entity("The getReportsByIdByDateRange" +
		   		" was called - GET sample report id " + id + " , the called id " + id
		   		+ " Dates are "+ startDate + " " + endDate).build();
   }
   	
}