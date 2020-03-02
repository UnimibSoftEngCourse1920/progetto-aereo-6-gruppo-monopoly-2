package services.ticketsale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import services.ticketsale.model.DtoSale;
import services.ticketsale.model.Flight;
import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;
import services.ticketsale.repository.FlightRepository;
import services.ticketsale.repository.SaleRepository;

@Path("/tickets/{code}/sale")
public class SaleController {

	@Context
	UriInfo uriInfo;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSale(@PathParam("code") String code) {
		
		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket(flight);
		Sale sale = new Sale("SALE0000", 3, ticket);
		SaleRepository.getInstance().save(sale);

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
		
		DtoSale resSale = new DtoSale(sale);
		
		List<Link> links = new ArrayList<>(); 
		links.add(self);
		links.add(confirm);
		resSale.setLinks(links);
		
		return Response.ok(resSale).links(self, confirm).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postSale(String jsonString) {
		
		ObjectMapper mapper = new ObjectMapper();

		DtoSale reqSale = null;
		try {
			reqSale = mapper.readValue(jsonString, DtoSale.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		Sale sale = SaleRepository.getInstance().find(reqSale.getCode());
		sale.setPaid(true);
		
		DtoSale resSale = new DtoSale(sale);
		
		return Response.ok(resSale).build();

	}
	
	/** Funzioni non definitive per il PUT
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