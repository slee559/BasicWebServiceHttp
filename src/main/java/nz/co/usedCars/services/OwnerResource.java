package nz.co.usedCars.services;

import java.net.URI;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nz.co.usedCars.domain.History;
import nz.co.usedCars.domain.Owner;
import nz.co.usedCars.domain.SecondHandVehicle;
import nz.co.usedCars.dto.dtoHistory;
import nz.co.usedCars.dto.dtoOwner;

/**
 * 
 * @author slee559
 *
 */
@Path("/owners")
public class OwnerResource {
	private static Logger _logger = LoggerFactory.getLogger(OwnerResource.class);
	private Executor executor = Executors.newSingleThreadExecutor();

	/**
	 * Handles incoming HTTP POST request. BASE_URI/owners/id Create an owner
	 * 
	 * @param dtoOwner
	 * @return
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	public Response createOwner(dtoOwner dtoOwner) {

		EntityManager em = PersistenceManager.instance().createEntityManager();
		Owner owner = DtoToDomainMapper.toDomainOwner(dtoOwner);
		em.getTransaction().begin();
		em.persist(owner);
		_logger.info("Created owner with id: " + owner.getId());
		em.getTransaction().commit();
		em.close();
		return Response.created(URI.create("/owners/" + owner.getId())).build();
	}

	/**
	 * Handles incoming HTTP GET request. BASE_URI/owners/id/vehicleLists
	 * 
	 * @param id
	 * @param response
	 */
	@GET
	@Path("{id}/vehicleLists")
	@Produces({ "application/xml", "application/json" })
	public void generateReport(@PathParam("id") final Long id, @Suspended final AsyncResponse response) {
		executor.execute(new Runnable() {
			public void run() {
				SecondHandVehicle report = generateReport(id);
				response.resume(DomainToDtoMapper.toDtoSecondHandVehicle(report));
			}
		});
	}

	/**
	 * Helper Method for getting a vehicle object
	 * 
	 * @param id
	 * @return
	 */
	protected SecondHandVehicle generateReport(Long id) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();
		Owner owner = em.find(Owner.class, id);

		SecondHandVehicle report = new SecondHandVehicle();
		for (SecondHandVehicle s : owner.getVehicle()) {

			if (id.equals(s.get_id())) {
				report = s;

				break;
			}
		}
		em.getTransaction().commit();
		em.close();
		return report;

	}

	/**
	 * Handles incoming HTTP GET request. BASE_URI/owners/id Returns an owner
	 * for given id.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({ "application/xml", "application/json" })
	public dtoOwner retrieveOwner(@PathParam("id") Long id) {
		_logger.info("Retrieving owner with id: " + id);
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();
		Owner owner = em.find(Owner.class, id);
		dtoOwner dtoOwner = DomainToDtoMapper.toDtoOwner(owner);
		em.getTransaction().commit();
		em.close();
		return dtoOwner;
	}

	/**
	 * Handles incoming HTTP PUT request. BASE_URI/owners/id/history Update the
	 * history to the given owner
	 * 
	 * @param id
	 * @param history
	 * @return
	 */
	@PUT
	@Path("{id}/history")
	@Consumes({ "application/xml", "application/json" })
	public Response addHistory(@PathParam("id") Long id, dtoHistory history) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();
		Owner owner = em.find(Owner.class, id);
		History domainHist = DtoToDomainMapper.toDomainHistory(history);
		domainHist.setOwner(owner);
		owner.addHistory(domainHist);
		em.persist(domainHist);
		em.getTransaction().commit();
		em.close();
		return Response.created(URI.create("/owners/" + owner.getId() + "/history")).build();
	}

	/**
	 * Handles incoming HTTP GET request. BASE_URI/owners/id/histories Returns
	 * the history of the given index
	 * 
	 * @param id
	 * @param index
	 * @return
	 */
	@GET
	@Path("{id}/histories")
	@Produces({ "application/xml", "application/json" })
	public dtoHistory retrieveHistory(@PathParam("id") Long id, @DefaultValue("1") @QueryParam("index") int index) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();

		Owner owner = em.find(Owner.class, id);
		Set<History> hist = owner.getHistory();

		dtoHistory history = new dtoHistory();
		for (History h : hist) {
			if (index == h.getId()) {
				history = DomainToDtoMapper.toDtoHistory(h);
				break;
			}
		}

		em.getTransaction().commit();
		em.close();
		return history;
	}
	
	/**
	 * HeaderParam is usually used for HATEAOUS purpose, redirection is done to get the Owner.
	 * HeaderParam specifies the id of the owner to retrieve.
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ "application/xml", "application/json" })
	public dtoOwner getOwnerByHeaderParam(@HeaderParam("id") String id) {
	
		_logger.info("Retrieving owner with id from HeaderParam: " + id);
		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();
		Owner owner = em.find(Owner.class, Long.valueOf(id).longValue());
		dtoOwner dtoOwner = DomainToDtoMapper.toDtoOwner(owner);
		em.getTransaction().commit();
		em.close();
		return dtoOwner;

	}

}
