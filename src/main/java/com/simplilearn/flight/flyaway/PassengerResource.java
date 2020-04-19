package com.simplilearn.flight.flyaway;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.simplilearn.flight.flyaway.entity.Passenger;
import com.simplilearn.flight.flyaway.entity.dao.PassengerDAO;


@Path("/passengers")
public class PassengerResource {

	@GET
    @Produces("application/json")
    public List<Passenger> getPassenger() {
		System.out.println("Connection Success");
        PassengerDAO dao = new PassengerDAO();
        List Passengers = dao.getPassengers();
        return Passengers;
    }
 
    
    @POST
    @Path ("/register")
    @Consumes("application/json")
    public Response addPassenger(Passenger Passenger){
                       
        PassengerDAO dao = new PassengerDAO();
        dao.addPassenger(Passenger);
        
        return Response.ok().build();
    }
    
    @POST
    @Path ("/login")
    @Consumes("application/json")
    public Response loginPassenger(Passenger Passenger){
        String resp = "" ;          
        PassengerDAO dao = new PassengerDAO();
        Passenger loadedPassenger = dao.getPassengerByEmail(Passenger);
        if(loadedPassenger !=null) {
        	if(loadedPassenger.getPassword().equals(Passenger.getPassword())) {
        		resp= "Login Successfull";
        	}else {
        		resp ="Login Faild Invalid Password";
        	}
        }
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updatePassenger(@PathParam("id") int id, Passenger Passenger){
        PassengerDAO dao = new PassengerDAO();
        int count = dao.updatePassenger(id, Passenger);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public Response deletePassenger(@PathParam("id") int id){
        PassengerDAO dao = new PassengerDAO();
        int count = dao.deletePassenger(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}

