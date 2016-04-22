package delectable.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import delectable.dto.OrderDetailDTO;
import delectable.dto.ReportAllOrdersDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.dto.ReportOrderDTO;
import delectable.dto.RevenueReportDTO;
import delectable.logic.ReportManager;
import delectable.patchhelper.PATCH;
import delectable.pojo.ReportTypes;

@Path("/report")
public class ReportService {

   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getReports() throws IllegalAccessException, InvocationTargetException, JsonProcessingException
   {

	   List<ReportTypes> repTyp; 
	   repTyp = ReportManager.reportMan.getReportTypes();
	   String jsonOutString;
	   ObjectMapper mapper = new ObjectMapper();
	   jsonOutString = mapper.writeValueAsString(repTyp);
	   
	   return Response.status(200).entity(jsonOutString).build();
   }
   
   @GET
   @Path("/{rid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getReportsByIdByDateRange(@PathParam("rid") int id, 
		   @QueryParam("start_date") String startDate, 
		   @QueryParam("end_date") String endDate) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, ParseException
   {
	   if(id == 800)
	   {
		   ReportOrderDTO repOr;// = new ReportOrderDTO();
		   DateFormat df = new SimpleDateFormat("yyyyMMdd");
		   Date dateobj = new Date();
		   //System.out.println(df.format(dateobj));
		   //oi.setOrder_date(df.format(dateobj));
		   repOr = ReportManager.reportMan.getOrderReport(id, df.format(dateobj));
		   
		   String jsonOutString;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOutString = mapper.writeValueAsString(repOr);
		   
		   return Response.status(200).entity(jsonOutString).build();
	   	}
	   
	   else if (id == 801)
	   {
		   ReportOrderDTO repOr;// = new ReportOrderDTO();
		   DateFormat df = new SimpleDateFormat("yyyyMMdd");
		   Date date = new Date();
		   
		   Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
		   calendar.setTime(date);
		   calendar.add(Calendar.DATE, 1);
		   date = calendar.getTime();
		   
		   //System.out.println(df.format(date));
		   //oi.setOrder_date(df.format(dateobj));
		   repOr = ReportManager.reportMan.getOrderReport(id,df.format(date));
		   
		   String jsonOutString;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOutString = mapper.writeValueAsString(repOr);
		   
		   return Response.status(200).entity(jsonOutString).build();
	   }
	   else if(id == 802)
	   {
		   
		   RevenueReportDTO repRev = ReportManager.reportMan.getRevenueReport(startDate, endDate);
		   String jsonOutString;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOutString = mapper.writeValueAsString(repRev);
		   return Response.status(200).entity(jsonOutString).build();
	   }
	   
	   else if(id == 803)
	   {
		   ReportAllOrdersDTO ordRev = ReportManager.reportMan.getOrdersReport(startDate, endDate);
		   String jsonOutString;
		   ObjectMapper mapper = new ObjectMapper();
		   jsonOutString = mapper.writeValueAsString(ordRev);
		   return Response.status(200).entity(jsonOutString).build();
	   }
	   else
		   return Response.status(400).entity("").build();
   }
   	
   
   
}