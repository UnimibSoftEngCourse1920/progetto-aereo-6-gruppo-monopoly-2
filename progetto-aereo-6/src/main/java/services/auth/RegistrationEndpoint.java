package services.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import services.auth.model.User;
import services.auth.repository.UserRepository;

@Path("/registration")
public class RegistrationEndpoint {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response registerUser(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			String token = register(username, password);
			return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	private String register(String username, String password) throws Exception {
		User user = UserRepository.getInstance().find(username);
		if(user != null) throw new Exception();
		
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setToken(username + "@RANDOMTOKEN");
		UserRepository.getInstance().save(user);
		
		return user.getToken();
	}

}