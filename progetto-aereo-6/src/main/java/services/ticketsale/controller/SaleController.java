package services.ticketsale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSale(@PathParam("code") String code, @QueryParam("quantity") int quantity) {

		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket(flight);

		Sale sale = new Sale();
		sale.getSale("UUIDCODE", quantity, ticket);
		SaleRepository.getInstance().save(sale);

		Link self = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("self")
				.type("GET")
				.build();

		Link confirm = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("confirm")
				.type("POST")
				.build();

		Link change = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("change")
				.type("PUT")
				.build();

		DtoSale resSale = new DtoSale();
		resSale.newSale(sale);

		List<Link> links = new ArrayList<>();
		links.add(self);
		links.add(confirm);
		links.add(change);
		resSale.setLinks(links);

		return Response.ok(resSale).links(self, confirm, change).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postSale(@HeaderParam("Authorization") String token, String jsonDtoSale) {

		ObjectMapper mapper = new ObjectMapper();
		DtoSale reqSale = null;
		try {
			reqSale = mapper.readValue(jsonDtoSale, DtoSale.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String username = token.replaceFirst("Bearer ", "");
		Customer customer = CustomerRepository.getInstance().find(username);

		Sale sale = SaleRepository.getInstance().find(reqSale.getCode());
		sale.confirmSale(customer, reqSale.getTicketHolderNames(), reqSale.getTicketHolderSurnames());

		Link self = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("self")
				.type("POST")
				.build();

		DtoSale resSale = new DtoSale();
		resSale.newSale(sale);

		List<Link> links = new ArrayList<>();
		links.add(self);
		resSale.setLinks(links);

		return Response.ok(resSale).links(self).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putSale(@PathParam("code") String code, String jsonDtoSale, @FormParam("quantity") int quantity) {

		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket(flight);

		ObjectMapper mapper = new ObjectMapper();
		DtoSale reqSale = null;
		try {
			reqSale = mapper.readValue(jsonDtoSale, DtoSale.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Sale sale = SaleRepository.getInstance().find(reqSale.getCode());
		try {
			sale.changeSale(quantity, ticket, reqSale.getTicketHolderNames(), reqSale.getTicketHolderSurnames());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sale);

		Link self = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("self")
				.type("PUT")
				.build();

		DtoSale resSale = new DtoSale();
		resSale.newSale(sale);

		List<Link> links = new ArrayList<>();
		links.add(self);
		resSale.setLinks(links);

		return Response.ok(resSale).links(self).build();
	}

	/*
	 * parse JSON ObjectMapper mapper = new ObjectMapper();
	 * 
	 * DtoSale reqSale = null; try { reqSale = mapper.readValue(jsonString,
	 * DtoSale.class); } catch (JsonMappingException e) { e.printStackTrace(); }
	 * catch (JsonProcessingException e) { e.printStackTrace(); }
	 * 
	 * Sale sale = SaleRepository.getInstance().find(reqSale.getCode());
	 * 
	 * DtoSale resSale = new DtoSale(sale);
	 * 
	 * return Response.ok(resSale).build();
	 */

	/**
	 * Funzioni non definitive per il PUT private static void takeSeat(Sale sale) {
	 * for(int i = 0; i < sale.getQuantity(); i++) { for(int j = 0; j <
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