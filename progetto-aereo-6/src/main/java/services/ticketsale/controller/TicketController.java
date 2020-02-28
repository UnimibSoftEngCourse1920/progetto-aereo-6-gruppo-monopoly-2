package services.ticketsale.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import services.ticketsale.model.Flight;
import services.ticketsale.model.Ticket;
import services.ticketsale.repository.FlightRepository;

/**
 * Questa classe rappresenta la nostra risorsa Ticket. Il path è da intendersi
 * relativo a quello definito all'application path. Quindi questa classe
 * risponde per tutte le chiamate agli URL del tipo /ticketsale/tickets
 */
@Path("/tickets")
public class TicketController {
	/**
	 * L'annotazione @Context inietta a runtime un'instanza dell'oggetto UriInfo
	 * uriInfo conterrà informazioni utili sull'URI richiesto come query parameters
	 * ed altro
	 */
	@Context
	UriInfo uriInfo;

	/**
	 * Questo metodo viene chiamato quando viene effettuata una GET su
	 * /ticketsale/tickets
	 * 
	 * @Produces definisce il MIME type della risposta in questo caso JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTickets() {

		// Generiamo un link self alla collezione di tickets.
		// uriInfo.getAbsolutePath() =
		// http://localhost:8080/progetto-aereo-6/ticketsale/tickets
		Link collection = Link.fromUri(uriInfo.getAbsolutePath()).title("tickets").rel("self").type("GET").build();

		// Accediamo al repository e prendiamo tutti i voloi in esso contenuti
		Collection<Flight> flights = FlightRepository.getInstance().findAll();
		List<Ticket> tickets = new ArrayList<>();

		// Per ogni volo presente settiamo il link self al ticket e generiamo una lista di tickets
		int i = 0;
		for (Flight e : flights) {
			List<Link> links = new ArrayList<>();
			tickets.add(new Ticket(e));
			links.add(Link.fromUri(uriInfo.getAbsolutePathBuilder().path(e.getCode()).build())
					.title("ticket")
					.rel("self")
					.type("GET")
					.build());
			tickets.get(i).setLinks(links);
			i++;
		}
		// Ritorniamo la risposta:
		// Response.ok setta automaticamente lo status code a 200
		// ed accetta anche il payload, in questo caso la nostra lista di voli
		// .links() accetta un numero infinito di parametri di tipo Link,
		// essi vengono inseriti dell'header della risposta
		return Response.ok(tickets).links(collection).build();
	}

	/**
	 * Questo metodo viene chiamato quando viene effettuata una GET su
	 * /ticketsale/tickets/{code}
	 * 
	 * @Path definisce un nuovo percorso relativo quello definito a livello di
	 *       classe
	 * @Produces definisce il MIME type della risposta
	 * @PathParam permette di accedere all'ultimo fragment dell'URI quello che per
	 *            noi rappresenta il code del ticket e lo inserisce nella
	 *            variabile String code
	 */
	@Path("/{code}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTicket(@PathParam("code") String code) {
		// Recuperiamo il volo grazie al repository
		Flight flight = FlightRepository.getInstance().find(code);
		Ticket ticket = new Ticket(flight);
		
		// Generiamo i link self al ticket ed il link collection all'intera collezione
		Link self = Link.fromUri(uriInfo.getAbsolutePath()).title("ticket").rel("self").type("GET").build();

		String absolutePath = uriInfo.getAbsolutePath().toString();
		Link collection = Link.fromUri(URI.create(absolutePath.substring(0, absolutePath.lastIndexOf('/'))))
				.title("tickets")
				.rel("collection")
				.type("GET")
				.build();

		// Generiamo il link next all'acquisto
		Link next = Link.fromUri(uriInfo.getAbsolutePathBuilder().path("sale").build())
				.title("sale")
				.rel("next")
				.type("POST")
				.build();

		// Aggiungiamo i link generati all'oggetto ticket
		List<Link> links = new ArrayList<>();
		links.add(collection);
		links.add(self);
		links.add(next);
		ticket.setLinks(links);

		// Ritorniamo la risposta:
		// Response.ok setta automaticamente lo status code a 200
		// ed accetta anche il payload, in questo caso il biglietto richiesto
		// .links() accetta un numero infinito di parametri di tipo Link,
		// essi vengono inseriti dell'header della risposta ma in questo caso
		// sono anche presenti nel payload perchè sono stati aggiunti
		// all'oggetto ticket
		return Response.ok(ticket).links(self, collection, next).build();
	}

}
