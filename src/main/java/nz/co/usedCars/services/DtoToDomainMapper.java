package nz.co.usedCars.services;


import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nz.co.usedCars.domain.*;
import nz.co.usedCars.dto.*;

/**
 * Mapper Class, converts dto objects to domain
 * @author slee559
 */
public class DtoToDomainMapper {
	
	private static Logger _logger = LoggerFactory.getLogger(DtoToDomainMapper.class);

	/**
	 * dtoOwner to domainOwner
	 * @param dtoOwner
	 * @return
	 */
	static Owner toDomainOwner(nz.co.usedCars.dto.dtoOwner dtoOwner){
		_logger.info("Mapping dtoOwner to domainOwner");
		Owner owner = new Owner();
		owner.setAdress(toDomainAddress(dtoOwner.get_adress()));
		owner.setFirstname(dtoOwner.get_firstname());
		owner.setSurname(dtoOwner.get_surname());
		_logger.info("Mapping dtoOwner to domainOwner done");
		return owner;
		
	}
	
	/**
	 * dtoAccident to domainAccident
	 * @param accident
	 * @return
	 */
	private static Accident toDomainAccident(dtoAccident accident){
		_logger.info("Mapping dtoAccident to domainAccident");
		Accident domainAccident = new Accident(accident.getDescription());
		_logger.info("Mapping dtoAccident to domainAccident done");
		return domainAccident;
		
	}
	
	/**
	 * dtoAddress to domainAddress
	 * @param dtoAdress
	 * @return
	 */
	private static Address toDomainAddress(dtoAddress dtoAdress){
		_logger.info("Mapping dtoAdress to domainAdress");
		Address domainAddress = new Address();
		domainAddress.setCity(dtoAdress.get_city());
		domainAddress.setPostCode(dtoAdress.get_postCode());
		domainAddress.setStreet(dtoAdress.get_street());
		domainAddress.setStreetNumber(dtoAdress.get_streetNumber());
		_logger.info("Mapping dtoAdress to domainAdress done");
		return domainAddress;
		
	}
	
	/**
	 * dtoHistory to domainHistory
	 * @param history
	 * @return
	 */
	static History toDomainHistory(dtoHistory history){
		_logger.info("Mapping dtoHistory to domainHistory");
		History hist = new History();
		hist.setAccident(toDomainAccident(history.get_accident()));
		_logger.info("Mapping dtoHistory to domainHistory done");
		return hist;
	}
	
	/**
	 * dtoCompany to domainCompany
	 * @param toDomainComapny
	 * @return
	 */
	static Company toDomainCompany(dtoCompany toDomainComapny){
		_logger.info("Mapping toDomainComapny to domainCompany");
		Company domainCompany = new Company();
		Set<Address> addresses = new HashSet<Address>();
		for(dtoAddress dtoAd:toDomainComapny.get_address() ){
			addresses.add(toDomainAddress(dtoAd));
		}
		if(addresses.size() != 0){
			domainCompany.set_address(addresses);
		}
		domainCompany.set_name(toDomainComapny.get_name());
		_logger.info("Mapping toDomainComapny to domainCompany done");
		return domainCompany;
		
	}
	
	/**
	 * dtoSecondHandVehcile to domainSecondHandVehicle.
	 * @param toDomainSecondHandVehicle
	 * @return
	 */
	static SecondHandVehicle toDomainSecondHandVehicle(dtoSecondHandVehicle toDomainSecondHandVehicle){
		_logger.info("Mapping dtoSecondHandVehicle to domainSecondHandVehicle");
		SecondHandVehicle secondHandVehicle = new SecondHandVehicle();
		secondHandVehicle.set_brand(toDomainSecondHandVehicle.get_brand());
		secondHandVehicle.set_type(toDomainSecondHandVehicle.get_type());
		secondHandVehicle.set_year(toDomainSecondHandVehicle.get_year());
		//secondHandVehicle.set_owner(toDomainOwner(toDomainSecondHandVehicle.get_owner()));
		_logger.info("Mapping dtoSecondHandVehicle to domainSecondHandVehicle done");
		return secondHandVehicle;
	}
}
