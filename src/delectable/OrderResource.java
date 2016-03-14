package delectable;

import java.io.InputStream;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderResource {

	//System.out.Println("orders  class here");


	@POST
	@Consumes("application/xml")
	public Response createOrder(InputStream is){
		System.out.println("asd");
		return Response.created(URI.create("/orders/12")).build();
	}
}
