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
import services.ticketsale.repository.FlightRepository;
/**
 * Questa classe rappresenta la nostra risorsa Flight.
 * Il path è da intendersi relativo a quello definito
 * all'application path.
 * Quindi questa classe risponde per tutte le chiamate
 * agli URL del tipo /api-ticketsale/flights
 */
@Path("/flights")
public class FlightController {
  /**
   * L'annotazione @Context inietta a runtime un'instanza
   * dell'oggetto UriInfo
   * uriInfo conterrà informazioni utili sull'URI richiesto come
   * query parameters ed altro
   */
  @Context
  UriInfo uriInfo;
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api-ticketsale/flights
   * 
   * @Produces definisce il MIME type della risposta
   * in questo caso JSON
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFlights() {
    
    // Generiamo un link self alla collezione di voli.
    // uriInfo.getAbsolutePath() = http://localhost:8080/Demo-Aereo-6/api-ticketsale/flights
    Link collection = Link.fromUri(uriInfo.getAbsolutePath())
        .title("flights")
        .rel("self")
        .type("GET")
        .build();
    
    // Accediamo al repository e prendiamo tutti i voloi in esso contenuti
    Collection<Flight> flights = FlightRepository.getInstance().findAll();
    
    // Per ogni volo presente settiamo il link self al volo
    for (Flight e : flights) {
      List<Link> links = new ArrayList<>();
      links.add(Link.fromUri(uriInfo.getAbsolutePathBuilder().path(e.getCode()).build())
          .title("flight")
          .rel("self")
          .type("GET")
          .build());
      e.setLinks(links);
    }
    // Ritorniamo la risposta:
    // Response.ok setta automaticamente lo status code a 200
    // ed accetta anche il payload, in questo caso la nostra lista di voli
    // .links() accetta un numero infinito di parametri di tipo Link
    // Essi vengono inseriti dell'header della risposta
    return Response.ok(flights).links(collection).build();
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api-ticketsale/flights/{code}
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta
   * @PathParam permette di accedere all'ultimo fragment dell'URI
   * quello che per noi rappresenta il code del biglietto e lo inserisce nella
   * variabile String code
   */
  @Path("/{code}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFlight(@PathParam("code") String code) {
    // Recuperiamo il volo grazie al repository
    Flight flight = FlightRepository.getInstance().find(code);
    
    // Generiamo i link self al volo ed il link collection all'intera collezione
    Link self = Link.fromUri(uriInfo.getAbsolutePath())
        .title("flight")
        .rel("self")
        .type("GET")
        .build();
    
    String absolutePath = uriInfo.getAbsolutePath().toString();
    Link collection = Link.fromUri(URI.create(absolutePath.substring(0, absolutePath.lastIndexOf('/'))))
        .title("flights")
        .rel("collection")
        .type("GET")
        .build();
    
    Link ticketL = Link.fromUri(uriInfo.getAbsolutePathBuilder().path("ticket").build())
    	.title("ticket")
    	.rel("next")
    	.type("GET")
    	.build();;
    
    // Aggiungiamo i link generati all'oggetto volo
    List<Link> links = new ArrayList<>();
    links.add(collection);
    links.add(self);
    links.add(ticketL);
    flight.setLinks(links);
    
    // Ritorniamo la risposta:
    // Response.ok setta automaticamente lo status code a 200
    // ed accetta anche il payload, in questo caso il biglietto richiesto
    // .links() accetta un numero infinito di parametri di tipo Link
    // Essi vengono inseriti dell'header della risposta ma in questo caso
    // sono anche presenti nel payload perchè sono stati aggiunti
    // all'oggetto volo
    return Response.ok(flight).links(self, collection, ticketL).build();
  }
  
}
