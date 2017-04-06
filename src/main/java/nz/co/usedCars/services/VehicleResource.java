package nz.co.usedCars.services;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nz.co.usedCars.domain.CarType;
import nz.co.usedCars.domain.Owner;
import nz.co.usedCars.domain.SecondHandVehicle;
import nz.co.usedCars.dto.dtoSecondHandVehicle;

/**
 * 
 * @author slee559
 *
 */
@Path("/vehicles")
public class VehicleResource {
	private static Logger _logger = LoggerFactory.getLogger(VehicleResource.class);
	
	/**
	 * Handles incoming HTTP POST request. BASE_URI/vehicles/id
	 * Creates a SecondHandVehicle to persist.
	 * @param id
	 * @param dtoSecondHandVehicle
	 * @return
	 */
	@POST
	@Path("{id}")
	@Consumes({"application/xml","application/json"})
	public Response createVehicle(@PathParam("id") Long id, dtoSecondHandVehicle dtoSecondHandVehicle) {
		
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();
		Owner owner = em.find(Owner.class, id);
		SecondHandVehicle domainSecond = DtoToDomainMapper.toDomainSecondHandVehicle(dtoSecondHandVehicle);
		domainSecond.set_owner(owner);
		owner.addVehicle(domainSecond);
		em.persist(domainSecond);	
		em.getTransaction().commit();
		_logger.info("Created vehicle with id: " + domainSecond.get_id());
		em.close();	
		return Response.created(URI.create("/vehicles/" + domainSecond.get_id())).build();
	}
	
	/**
	 * Handles incoming HTTP PUT request. BASE_URI/vehicles/id
	 * Updates the CarType with CookiParam.
	 * @param id
	 * @param type
	 */
	@PUT
	@Path("{id}")
	@Consumes({"application/xml","application/json"})
	public void updateVehicle(@PathParam("id") Long id, @CookieParam("carType") Cookie type){
		if(type != null){
			EntityManager em = PersistenceManager.instance().createEntityManager();
			em.getTransaction().begin();
			Owner owner = em.find(Owner.class, id);
			if(owner.getVehicle().size() != 0){
				for(SecondHandVehicle s : owner.getVehicle()){
					if(type.getValue().equals("AUTOMATIC")){
						s.set_type(CarType.AUTOMATIC);
					}else{
						s.set_type(CarType.MANUAL);
					}
				}
			}
			_logger.info("Vehicle is update for owner id: " + owner.getId());
			em.getTransaction().commit();
			em.close();	
		}
		
	}
	
	/**
	 * Handles incoming HTTP GET request. BASE_URI/vehicles/cartype/id
	 * Returns a dtoSecondHandVehicle object.
	 * @param id
	 * @param type
	 * @return
	 */
	@GET
	@Path("cartype/{id}")
	@Produces({"application/xml","application/json"})
	public dtoSecondHandVehicle getVehicle(@PathParam("id") Long id, @MatrixParam("CarType") String type ){
		CarType t = CarType.fromString(type);
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();
		Owner owner = em.find(Owner.class, id);
		dtoSecondHandVehicle dto = new dtoSecondHandVehicle();
		for(SecondHandVehicle s : owner.getVehicle()){
			if(s.get_type().equals(t)){
				dto = DomainToDtoMapper.toDtoSecondHandVehicle(s);
			}
		}
		return dto;
		
	}
}
