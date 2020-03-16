package services.ticketsale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import services.auth.provider.Secured;
import services.ticketsale.model.Flight;
import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;
import services.ticketsale.model.dto.DtoSale;
import services.ticketsale.repository.FlightRepository;
import services.ticketsale.repository.SaleRepository;

@Path("/tickets/{code}/change/{salecode}")
public class ChangeController {

	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getChange(@PathParam("salecode") String saleCode) {

		Sale sale = SaleRepository.getInstance().find(saleCode);
		
		DtoSale resSale = new DtoSale();
		System.out.println(sale);
		resSale.buildDtoSale(sale);

		Link change = Link.fromUri(uriInfo.getAbsolutePath())
				.title("sale")
				.rel("change")
				.type("POST")
				.build();

		List<Link> links = new ArrayList<>();
		links.add(change);
		resSale.setLinks(links);

		return Response.ok(resSale).links(change).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	public Response postChange(@PathParam("code") String code, String jsonDtoSale) {

		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket();
		ticket.incompleteTicket(flight);

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
			sale.changeSale(reqSale.getQuantity(), ticket, reqSale.getTicketHolderNames(), reqSale.getTicketHolderSurnames());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sale);

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