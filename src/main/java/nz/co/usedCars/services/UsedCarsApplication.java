package nz.co.usedCars.services;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Application class to register classes.
 * @author slee559
 *
 */
@ApplicationPath("/services")
public class UsedCarsApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public UsedCarsApplication()
	{
		singletons.add(new PersistenceManager());
		OwnerResource ownerResource = new OwnerResource();
	    singletons.add(ownerResource);
	    VehicleResource vehicleResource = new VehicleResource();
	    singletons.add(vehicleResource);
	
	}

	@Override
	public Set<Object> getSingletons()
	{
		return singletons;
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

}
