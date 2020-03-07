package services.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
import services.customer.model.Customer;
import services.customer.model.dto.DtoCustomer;

@Path("")
public class CustomerController {

	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getCustomer(@HeaderParam("Authorization") String token) {
		
		token = token.replaceFirst("Bearer ", "");
		int i = token.indexOf("@");
		String username = token.substring(0, i);
		User user = UserRepository.getInstance().find(username);
		
		Link self = Link.fromUri(uriInfo.getAbsolutePath())
				.title("customer")
				.rel("self")
				.type("GET")
				.build();
		
		Link create = Link.fromUri(uriInfo.getAbsolutePath())
				.title("customer")
				.rel("create")
				.type("POST")
				.build();
		
		List<Link> links = new ArrayList<>();
		
		DtoCustomer resCustomer = new DtoCustomer();
		
		if(!(user instanceof Customer)) {
			resCustomer.newCustomer(username, "", "", "", 0);
			links.add(self);
			links.add(create);
			resCustomer.setLinks(links);
			return Response.ok(resCustomer).links(self, create).build();
		}

		resCustomer.newCustomer(username, ((Customer) user).getName(), ((Customer) user).getSurname(),
				((Customer) user).getEmail(), ((Customer) user).getPoint());
		
		links.add(self);
		resCustomer.setLinks(links);
		
		return Response.ok(resCustomer).links(self).build();
	} 

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	public Response postCustomer(String jsonDtoCustomer) {
		
		ObjectMapper mapper = new ObjectMapper();
		DtoCustomer reqCustomer = null;
		try {
			reqCustomer = mapper.readValue(jsonDtoCustomer, DtoCustomer.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		User user = UserRepository.getInstance().find(reqCustomer.getUsername());
		
		if(user instanceof Customer)
			return Response.status(Response.Status.FORBIDDEN).build();
		
		Customer customer = new Customer();
		customer.setUsername(user.getUsername());
		customer.setPassword(user.getPassword());
		customer.setToken(user.getToken());
		customer.subscribe(reqCustomer.getName(), reqCustomer.getSurname(), reqCustomer.getEmail());
		UserRepository.getInstance().update(customer.getUsername(), customer);
		
		
		Link prev = Link.fromUri(uriInfo.getAbsolutePath())
				.title("customer")
				.rel("prev")
				.type("GET")
				.build();
		
		DtoCustomer resCustomer = new DtoCustomer();
		resCustomer.newCustomer(customer.getUsername(), customer.getName(), customer.getSurname(),
				customer.getEmail(), customer.getPoint());
		
		List<Link> links = new ArrayList<>();
		links.add(prev);
		resCustomer.setLinks(links);
		
		return Response.ok(resCustomer).links(prev).build();
	} 
}