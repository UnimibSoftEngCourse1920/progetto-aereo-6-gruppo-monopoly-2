package services.ticketsale.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import services.ticketsale.model.Flight;
import services.ticketsale.model.Sale;
import services.ticketsale.model.Ticket;
import services.ticketsale.repository.FlightRepository;

@Path("/flights/{code}/ticket")
public class TicketController {
  
  @Context
  UriInfo uriInfo;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTicket(@PathParam("code") String code) {
	  
    Flight flight = FlightRepository.getInstance().find(code);
    
    Link self = Link.fromUri(uriInfo.getAbsolutePath())
        .title("ticket")
        .rel("self")
        .type("GET")
        .build();
    
    String absolutePath = uriInfo.getAbsolutePath().toString();
    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf('/'));
    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf('/'));
    Link collection = Link.fromUri(URI.create(absolutePath))
        .title("flights")
        .rel("collection")
        .type("GET")
        .build();
    
    Link saleL = Link.fromUri(uriInfo.getAbsolutePathBuilder().path("sale").build())
        .title("sale")
        .rel("next")
        .type("POST")
        .build();
    
    absolutePath = uriInfo.getAbsolutePath().toString();
    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf('/'));
    Link flightL = Link.fromUri(URI.create(absolutePath))
    	.title("flight")
    	.rel("prev")
    	.type("GET")
    	.build();
    
    Ticket ticket = new Ticket(flight);
    
    List<Link> links = new ArrayList<>();
    links.add(collection);
    links.add(self);
    links.add(saleL);
    links.add(flightL);
    ticket.setLinks(links);
    
    return Response.ok(ticket).links(self, collection, saleL, flightL).build();
  }
  
  @Path("/sale")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getSale(Ticket ticket, 
		  					@FormParam("quantity") int quantity) {
    
    Link self = Link.fromUri(uriInfo.getAbsolutePath())
        .title("sale")
        .rel("self")
        .type("POST")
        .build();
    
    double totPrice;
    int baseQ = 1;
    if(baseQ >= quantity)
    	quantity = baseQ;
    
    totPrice = ticket.getFlight().getPrice() * quantity;
    Sale sale = new Sale("AAA", quantity, totPrice, ticket);
    
    List<Link> links = new ArrayList<>();
    links.add(self);
    sale.setLinks(links);
    
    return Response.ok(sale).links(self).build();
  }
  
}