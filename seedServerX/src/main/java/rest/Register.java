package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import entity.Role;
import facades.ICollectiveFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.CollectiveFacadeFactory;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import security.Secret;

@Path("register")
public class Register {

    ICollectiveFacade uf = CollectiveFacadeFactory.getInstance();
    Gson gs = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSomething(String content) {
        User user = gs.fromJson(content, User.class);
        uf.registerUser(user);
        JsonObject responseJson = new JsonObject();
        List<String> roles = new ArrayList();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        
        try {
            String token = createToken(user.getUserName(), roles);
            responseJson.addProperty("username", user.getUserName());
            responseJson.addProperty("token", token);
            return Response.ok(new Gson().toJson(responseJson)).build();
        } catch (JOSEException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotAuthorizedException("Couldn't Create User", Response.Status.UNAUTHORIZED);
        }
    }

    private String createToken(String subject, List<String> roles) throws JOSEException {
        StringBuilder res = new StringBuilder();
        for (String string : roles) {
            res.append(string);
            res.append(",");
        }
        String rolesAsString = res.length() > 0 ? res.substring(0, res.length() - 1) : "";
        String issuer = "semester3demo-cphbusiness.dk-computerScience";

        JWSSigner signer = new MACSigner(Secret.SHARED_SECRET);
        Date date = new Date();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .claim("username", subject)
                .claim("roles", roles)
                .claim("issuer", issuer)
                .issueTime(date)
                .expirationTime(new Date(date.getTime() + 1000 * 60 * 60))
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

}
