package services.ticketsale.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Link;

import com.google.gson.Gson;

import services.ticketsale.model.Flight;
import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;
import services.ticketsale.repository.FlightRepository;

@Path("/tickets/{code}/sale")
public class SaleController {

	@Context
	UriInfo uriInfo;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postSale(String jsonString /*@FormParam("quantity") int quantity*/) {

		System.out.println(jsonString);
		
		Ticket ticket = new Ticket(jsonString);
		System.out.println(ticket.getCode());

		Link self = Link.fromUri(uriInfo.getAbsolutePath()).title("sale").rel("self").type("POST").build();

		Link confirm = Link.fromUri(uriInfo.getAbsolutePath()).title("sale").rel("next").type("PUT").build();

		// Trovare altri metodi per selezionare il codice "AAA"
		Sale sale = new Sale("AAA", 2, ticket);

		System.out.println(sale.getCode());
		System.out.println(sale.getSaleDate());
		System.out.println(sale.getTotPrice());
	
		
		
		List<Link> links = new ArrayList<>();
		links.add(self);
		links.add(confirm);
		sale.setLinks(links);
		
		return Response.ok(sale).links(self, confirm).build();
	}

	@Path("/sale")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmSale(Sale sale) {

		// takeSeat(sale);
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