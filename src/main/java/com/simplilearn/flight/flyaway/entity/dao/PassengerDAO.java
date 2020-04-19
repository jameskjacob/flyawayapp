package com.simplilearn.flight.flyaway.entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simplilearn.flight.flyaway.entity.Passenger;
import com.simplilearn.flight.flyaway.entity.util.SessionUtil;

public class PassengerDAO {
	
	public void addPassenger(Passenger bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addPassenger(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addPassenger(Session session, Passenger bean){
        Passenger passenger = new Passenger();
      passenger.setEmail(bean.getEmail());
      passenger.setFirstName(bean.getFirstName());
      passenger.setId(bean.getId());
      passenger.setLastName(bean.getLastName());
      passenger.setPassword(bean.getPassword());
        session.save(passenger);
    }
    
    public List<Passenger> getPassengers(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Passenger");
        List<Passenger> passengers =  query.list();
        session.close();
        return passengers;
    }
    public Passenger getPassengerByEmail(Passenger passenger){ 
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Passenger where email:=email");
        query.setParameter("email", passenger.getEmail());
        Passenger passengers =  (Passenger)query.getResultList().get(0);
        session.close();
        return passengers;
    }
    public int deletePassenger(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Passenger where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
 
	public int updatePassenger(int id, Passenger passenger){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Passenger set email:= email , firstName:= firstName, lastName:=lastName, password :=password where id := id";
            Query query = session.createQuery(hql);
            
            query.setInteger("id",id);
            query.setString("email",passenger.getEmail());
            query.setString("firstName",passenger.getFirstName());
            query.setString("lastName",passenger.getLastName());
            query.setString("password",passenger.getPassword());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}
