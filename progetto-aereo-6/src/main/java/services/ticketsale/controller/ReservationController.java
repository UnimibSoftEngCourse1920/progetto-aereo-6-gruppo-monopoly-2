package services.ticketsale.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import services.auth.model.User;
import services.auth.provider.Secured;
import services.auth.repository.UserRepository;
import services.ticketsale.model.Flight;
import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;
import services.ticketsale.model.dto.DtoSale;
import services.ticketsale.repository.FlightRepository;
import services.ticketsale.repository.SaleRepository;

@Path("/tickets/{code}/reservation")
public class ReservationController {

	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservation(@PathParam("code") String code, 
			@QueryParam("quantity") int quantity) {
		
		Flight flight = FlightRepository.getInstance().find(code);
		
		Ticket ticket = new Ticket();
		ticket.incompleteTicket(flight);
		
		Sale sale = new Sale();
		String saleCode = UUID.randomUUID().toString();
		sale.incompleteSale(saleCode, quantity, ticket);
		SaleRepository.getInstance().save(sale);
		
		DtoSale resSale = new DtoSale();
		resSale.buildIncompleteDtoSale(sale);

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

		List<Link> links = new ArrayList<>();
		links.add(self);
		links.add(confirm);
		resSale.setLinks(links);

		return Response.ok(resSale).links(self, confirm).build();	
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	public Response postReservation(@HeaderParam("Authorization") String token, String jsonDtoSale) {

		ObjectMapper mapper = new ObjectMapper();
		DtoSale reqSale = null;
		try {
			reqSale = mapper.readValue(jsonDtoSale, DtoSale.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		token = token.replaceFirst("Bearer ", "");
		int i = token.indexOf("@");
		String username = token.substring(0, i);
		User customer = UserRepository.getInstance().find(username);

		Sale sale = SaleRepository.getInstance().find(reqSale.getCode());
		sale.reservation(customer, reqSale.getTicketHolderNames(), reqSale.getTicketHolderSurnames());

		Link prev = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("prev")
				.type("GET")
				.build();

		DtoSale resSale = new DtoSale();
		resSale.buildDtoSale(sale);

		List<Link> links = new ArrayList<>();
		links.add(prev);
		resSale.setLinks(links);

		return Response.ok(resSale).links(prev).build();
	}
}