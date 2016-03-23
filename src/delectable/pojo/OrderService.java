package delectable.pojo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/orders")
public class OrderService {

   //UserDao userDao = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.TEXT_PLAIN)
   public String getUsers(){
	   System.out.println("I was called!");
	   return "orderssss";
   }
   
   /*public List<User> getUsers(){
      return userDao.getAllUsers();
   }*/	
}