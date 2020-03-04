package services.ticketsale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import services.auth.provider.Secured;
import services.customer.model.Customer;
import services.customer.repositiry.CustomerRepository;
import services.ticketsale.model.Flight;
import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;
import services.ticketsale.model.dto.DtoSale;
import services.ticketsale.repository.FlightRepository;
import services.ticketsale.repository.SaleRepository;

@Path("/tickets/{code}/sale")
public class SaleController {

	@Context
	UriInfo uriInfo;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response postSale(@PathParam("code") String code,
			@FormParam("username") String username) {
		
		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket(flight);
		Customer customer = CustomerRepository.getInstance().find(username);
		Sale sale = new Sale("SALE0000", 3, ticket, customer);
		SaleRepository.getInstance().save(sale);

		Link self = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("self")
				.type("POST")
				.build();

		Link confirm = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("change")
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
	public Response putSale(@PathParam("code") String code,
			@HeaderParam("salecode") String salecode,
			@FormParam("quantity") int quantity) {
		
		System.out.println(quantity);
		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket(flight);
		System.out.println(ticket);
		Sale sale = SaleRepository.getInstance().find(salecode);
		System.out.println(sale);
		try {
			sale.changeSale(ticket, quantity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sale);
		DtoSale resSale = new DtoSale(sale);
		return Response.ok(resSale).build();

	}
	
	/* parse JSON
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
	
	DtoSale resSale = new DtoSale(sale);
	
	return Response.ok(resSale).build();
	*/
	
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