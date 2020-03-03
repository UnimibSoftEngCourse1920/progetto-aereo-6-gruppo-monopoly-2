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

@Path("/authentication")
public class AuthenticationEndpoint {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {

		try {
			
			String token = authenticate(username, password);
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	private String authenticate(String username, String password) throws Exception {
		User user = UserRepository.getInstance().find(username);
		if(!password.equals(user.getPassword())) throw new Exception();
		String token = "RANDOMTOKEN";
		user.setToken(token);
		return token;
	}

}
