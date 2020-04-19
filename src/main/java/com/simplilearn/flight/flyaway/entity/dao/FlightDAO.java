package com.simplilearn.flight.flyaway.entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simplilearn.flight.flyaway.entity.Flight;
import com.simplilearn.flight.flyaway.entity.util.SessionUtil;

public class FlightDAO {
	
	public void addFlight(Flight bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addFlight(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addFlight(Session session, Flight bean){
        Flight flight = new Flight();
      flight.setId(bean.getId());
      flight.setArrival(bean.getArrival());
      flight.setDeparture(bean.getDeparture());
      flight.setArrivalDate(bean.getArrivalDate());
      flight.setDepartureDate(bean.getDepartureDate());
        session.save(flight);
    }
    
    public List<Flight> getFlights(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Flight");
        List<Flight> Flights =  query.list();
        session.close();
        return Flights;
    }
    public Flight getFlightById(Flight flight){ 
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Flight where id:=id");
        query.setParameter("email", flight.getId());
        Flight flights =  (Flight)query.getResultList().get(0);
        session.close();
        return flights;
    }
    public int deleteFlight(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Flight where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
 
	public int updateFlight(int id, Flight Flight){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Flight set email:= email , firstName:= firstName, lastName:=lastName, password :=password where id := id";
            Query query = session.createQuery(hql);
            
            query.setInteger("id",id);
            query.setString("departure",Flight.getDeparture());
            query.setString("arrival",Flight.getArrival());
            query.setLocalDateTime("arrivalDate",Flight.getArrivalDate());
            query.setLocalDateTime("departureDate",Flight.getDepartureDate());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}
