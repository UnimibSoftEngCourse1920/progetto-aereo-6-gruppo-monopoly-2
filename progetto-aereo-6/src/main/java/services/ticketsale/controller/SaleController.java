package services.ticketsale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;

@Path("/tickets/{code}/sale")
public class SaleController {

	@Context
	UriInfo uriInfo;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postSale(String jsonString /*@FormParam("quantity") int quantity*/) {

		// Type founderListType = new TypeToken<ArrayList<Link>>(){}.getType();  
		//Gson gson = new Gson();
		//Ticket ticket = gson.fromJson(jsonString, Ticket.class);
		Ticket ticket = new Ticket(jsonString);
		
		System.out.println(ticket);

		Link self = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("self")
				.type("POST")
				.build();

		Link confirm = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("confirm")
				.type("PUT")
				.build();

		// Trovare altri metodi per selezionare il codice "AAA"
		Sale sale = new Sale("AAA", 2, ticket);
		
		System.out.println(sale);
		
		List<Link> links = new ArrayList<>();
		links.add(self);
		links.add(confirm);
		sale.setLinks(links);
		
		return Response.ok(sale).links(self, confirm).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmSale(String jsonString) {
		
		Sale sale = new Sale(jsonString);
		
		return Response.ok(sale).build();

	}
	/**
	 * private static void takeSeat(Sale sale) { for(int i = 0; i <
	 * sale.getQuantity(); i++) { for(int j = 0; j <
	 * sale.getTicket(i).getFlight().getSeatsNumber(); j++) {
	 * if(!sale.getTicket(i).getFlight().getSeats(j)) {
	 * sale.getTicket(i).setSeat(j); sale.getTicket(i).getFlight().setSeats(j,
	 * true); } } } }
	 * 
	 * private static void freeSeat(Sale sale) { for(int i = 0; i <
	 * sale.getQuantity(); i++) {
	 * if(sale.getTicket(i).getFlight().getSeats(sale.getTicket(i).getSeat())) {
	 * sale.getTicket(i).getFlight().setSeats(sale.getTicket(i).getSeat(), false);
	 * sale.getTicket(i).setSeat(0); } } }
	 **/

}