package nz.co.usedCars;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nz.co.usedCars.domain.Accident;
import nz.co.usedCars.domain.Address;
import nz.co.usedCars.domain.CarType;
import nz.co.usedCars.domain.Company;
import nz.co.usedCars.domain.History;
import nz.co.usedCars.domain.Owner;
import nz.co.usedCars.domain.SecondHandVehicle;
import nz.co.usedCars.dto.dtoAccident;
import nz.co.usedCars.dto.dtoAddress;
import nz.co.usedCars.dto.dtoHistory;
import nz.co.usedCars.dto.dtoOwner;
import nz.co.usedCars.dto.dtoSecondHandVehicle;
import nz.co.usedCars.services.PersistenceManager;

/**
 * Test cases for JAX-RS mainly. JPA and ORM-based persistence goes with them.
 * 
 * @author slee559
 *
 */
public class CommunicationTest {
	private Logger _logger = LoggerFactory.getLogger(CommunicationTest.class);

	@Test
	public void testOwnerResource()
			throws JAXBException, IllegalStateException, SQLException, ClassNotFoundException, InterruptedException {

		Client client = ClientBuilder.newClient();
		try {

			
			// Test for posting an owner with xml.
			_logger.info("Creating a new owner with xml");
			dtoAddress dtoAd = new dtoAddress("39", "Makora Rd", "Auckland", 0614);
			dtoOwner dtoOwner = new dtoOwner();
			dtoOwner.set_adress(dtoAd);
			dtoOwner.set_firstname("Brian");
			dtoOwner.set_surname("Lee");
			Response response = client.target("http://localhost:13000/services/owners").request()
					.post(Entity.xml(dtoOwner));
			int status = response.getStatus();
			if (status != 201) {
				_logger.error("Failed to create the owner with xml ; Web service responded with: " + status);
				fail();
			}
			String location = response.getLocation().toString();
			_logger.info("URI for new Owner: " + location);
			response.close();

			
			// Test for retrieving the posted owner with xml
			dtoOwner owner = client.target(location).request().accept("application/xml").get(dtoOwner.class);
			_logger.info("Retrieved the Owner with xml: " + location);
			assertEquals(owner.get_firstname(), "Brian");
			assertEquals(owner.get_surname(), "Lee");
			_logger.info("Succefually retrieved with xml: " + owner.get_id());

			
			// Test for retrieving the posted owner with json
			dtoOwner owner1 = client.target(location).request().accept("application/json").get(dtoOwner.class);
			_logger.info("Retrieved the Owner with JSON: " + location);
			assertEquals(owner1.get_firstname(), "Brian");
			assertEquals(owner1.get_surname(), "Lee");
			_logger.info("Succefually retrieved with JSON: " + owner1.get_id());

			
			// Test for updating the owner's hisotry with xml
			dtoHistory dtoHistory = new dtoHistory();
			_logger.info("Updating history: " + location + "/history");
			dtoAccident dtoAccident = new dtoAccident("car-crash");
			dtoHistory.set_accident(dtoAccident);
			response = client.target(location + "/history").request().put(Entity.xml(dtoHistory));
			status = response.getStatus();
			if (status != 201) {
				_logger.error("Failed to update history; Web service responded with: " + status);
				fail();
			}
			_logger.info("Succefually updated history: " + location);
			response.close();

			
			// Test for QueryParam with xml.
			_logger.info("Retrieving the history with Queryparm with xml");
			dtoHistory histories = client.target(location + "/histories?index=1").request().accept("application/xml")
					.get(dtoHistory.class);
			assertEquals(histories.get_id() + "", 1 + "");
			_logger.info("Succefully retrieved history with xml: " + histories.get_id());

			// Test for creating and posting to persist SeconHandVehicle with xml.
			_logger.info("Posting vehicle with xml");
			dtoSecondHandVehicle dtoSecondHandVehicle = new dtoSecondHandVehicle();
			dtoSecondHandVehicle.set_brand("Honda");
			dtoSecondHandVehicle.set_type(CarType.MANUAL);
			dtoSecondHandVehicle.set_year("2006");
			response = client.target("http://localhost:13000/services/vehicles/" + owner.get_id()).request()
					.post(Entity.xml(dtoSecondHandVehicle));
			status = response.getStatus();
			if (status != 201) {
				_logger.error("Failed to create vehicle with xml; Web service responded with: " + status);
				fail();
			}
			response.close();

			/*
			 * Test for Async. Assume this takes a large time. I tried to return
			 * List of SecondHandVehicle instances, but could not manage to do
			 * it. So it can be a heavy operation. This still returns the
			 * dtoSecondVehicle object asynchronously
			 */
			_logger.info("Start the heavy operation");
			Client client1 = ClientBuilder.newClient();
			client1.target("http://localhost:13000/services/owners/1/vehicleLists").request().async()
					.get(new InvocationCallback<dtoSecondHandVehicle>() {
						@Override
						public void completed(dtoSecondHandVehicle response) {
							_logger.info("Currently owned vehicle is: " + "brand:" + response.get_brand() + " "
									+ "year:" + response.get_year() + " " + "type:" + response.get_type());
						}

						@Override
						public void failed(Throwable throwable) {
							throwable.printStackTrace();
						}

					});
			/*
			 * Sleep for a second. This is just to keep the order of the test.
			 * The sync does not have to be in that place. As long as there is
			 * an owner and SecondHandVehicle, this works.
			 */
			Thread.sleep(1000);

			
			// Update MANUAL to AUTOMATIC with cookie
			_logger.info("Updating the vehicle with cookie");
			NewCookie cookie = new NewCookie("carType", "AUTOMATIC");
			client.target("http://localhost:13000/services/vehicles/" + owner.get_id()).request().cookie(cookie)
					.put(null);

			
			// Test to retrieve the dtoSecondVehicle using MatrixParam with xml.
			_logger.info("Retrieving the vehicle using MatrixParam with xml");
			dtoSecondHandVehicle dtoSecondWithMatrixParam = client
					.target("http://localhost:13000/services/vehicles/cartype/" + owner.get_id() + ";CarType=AUTOMATIC")
					.request().accept("application/xml").get(dtoSecondHandVehicle.class);
			assertEquals(dtoSecondWithMatrixParam.get_id() + "", 1 + "");
			_logger.info("Succefully updated and retreived with xml.");

			
			/* Test for creating and posting to persist SeconHandVehicle with json.
			 * Json for retrieving was tested, but not posting(posting will contain update so PUT was not tested).
			 */
			_logger.info("Posting the vehicle with json");
			dtoSecondHandVehicle dtoSecondHandVehicleJson = new dtoSecondHandVehicle();
			dtoSecondHandVehicleJson.set_brand("Lexus");
			dtoSecondHandVehicleJson.set_type(CarType.MANUAL);
			dtoSecondHandVehicleJson.set_year("2016");
			response = client.target("http://localhost:13000/services/vehicles/" + owner.get_id()).request()
					.post(Entity.json(dtoSecondHandVehicleJson));
			status = response.getStatus();
			if (status != 201) {
				_logger.error("Failed to create vehicle with json; Web service responded with: " + status);
				fail();
			}
			response.close();

			
			// Test to retrieve the dtoSecondVehicle, which is posted with json, using MatrixParam with json.
			_logger.info("Retrieving the vehicle using MatrixParam with josn");
			dtoSecondHandVehicle dtoSecondWithMatrixParamJson = client
					.target("http://localhost:13000/services/vehicles/cartype/" + owner.get_id() + ";CarType=MANUAL").request()
					.accept("application/json").get(dtoSecondHandVehicle.class);
			assertEquals(dtoSecondWithMatrixParamJson.get_id() + "", 2 + "");
			_logger.info("Succefully retreived with json.");
			
			
			//Test to retrieve the owner using HeaderParam. The HeaderParam is not used well here, but I can't really think of one.
			_logger.info("Retrieving the dtoOwner using HeaderParam with xml");
			dtoOwner dtoOwnerHeaderParam = client.target("http://localhost:13000/services/owners").request().accept("application/xml").header("id", "1")
					.get(dtoOwner.class);
			assertEquals(dtoOwnerHeaderParam.get_id()+"", 1+"");
			_logger.info("Succefully retreived the owner using HeaderParam with xml: " + dtoOwnerHeaderParam.get_id() );
			
		} finally {
			// Release any connection resources.
			client.close();
		}

	}

	/*
	 * Unused method, might be use for future work.
	 */
	public static void reloadDatabase() {

		EntityManager em = PersistenceManager.instance().createEntityManager();
		em.getTransaction().begin();

		SecondHandVehicle secondhandvehicle = new SecondHandVehicle();
		secondhandvehicle.set_brand("Honda");
		secondhandvehicle.set_type(CarType.AUTOMATIC);
		secondhandvehicle.set_year("2007");
		em.persist(secondhandvehicle);
		SecondHandVehicle secondhandvehicle2 = new SecondHandVehicle();
		secondhandvehicle2.set_brand("Mazda");
		secondhandvehicle2.set_type(CarType.MANUAL);
		secondhandvehicle2.set_year("2007");
		em.persist(secondhandvehicle2);

		List<SecondHandVehicle> vehicles = new ArrayList<SecondHandVehicle>();
		vehicles.add(secondhandvehicle);
		vehicles.add(secondhandvehicle2);
		Address address = new Address();
		address.setCity("Auckland");
		address.setPostCode(0614);
		address.setStreet("Queen Street");
		address.setStreetNumber("111");

		Address address1 = new Address();
		address1.setCity("ChristChurch");
		address1.setPostCode(1111);
		address1.setStreet("Christ Street");
		address1.setStreetNumber("222");

		Address address2 = new Address();
		address2.setCity("New York");
		address2.setPostCode(2222);
		address2.setStreet("New Street");
		address2.setStreetNumber("222");

		Set<Address> companyAddress = new HashSet<Address>();
		companyAddress.add(address1);
		companyAddress.add(address2);

		Company justCars = new Company();
		em.persist(justCars);
		justCars.set_address(companyAddress);
		justCars.set_name("Just Cars");
		Set<Company> companies = new HashSet<Company>();
		companies.add(justCars);

		Owner owner = new Owner();
		owner.setAdress(address);
		owner.setFirstname("Kristen");
		owner.setSurname("Pyo");
		owner.setComapnies(companies);
		owner.set_vehicle(vehicles);
		em.persist(owner);
		secondhandvehicle.set_owner(owner);
		secondhandvehicle2.set_owner(owner);

		History history1 = new History();
		history1.setAccident(new Accident("light accident"));
		history1.setOwner(owner);

		owner.addHistory(history1);
		em.persist(history1);
		em.getTransaction().commit();
		em.close();
	}

}