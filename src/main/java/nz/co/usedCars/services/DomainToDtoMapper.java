package nz.co.usedCars.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nz.co.usedCars.domain.Accident;
import nz.co.usedCars.domain.Address;
import nz.co.usedCars.domain.History;
import nz.co.usedCars.domain.Owner;
import nz.co.usedCars.domain.SecondHandVehicle;
import nz.co.usedCars.dto.dtoAccident;
import nz.co.usedCars.dto.dtoAddress;
import nz.co.usedCars.dto.dtoHistory;
import nz.co.usedCars.dto.dtoOwner;
import nz.co.usedCars.dto.dtoSecondHandVehicle;

/**
 * Mapper Class, converts domain objects to dto
 * @author slee559
 */
public class DomainToDtoMapper {
	private static Logger _logger = LoggerFactory.getLogger(DomainToDtoMapper.class);
	
	/**
	 * domainOwner to dtoOwner.
	 * @param owner
	 * @return
	 */
	static dtoOwner toDtoOwner(Owner owner){
		_logger.info("Mapping domainOwner to dtoOwner");
		dtoOwner dtoOwner = new dtoOwner();
		dtoOwner.set_adress(toDtoAddress(owner.getAdress()));
		dtoOwner.set_firstname(owner.getFirstname());
		dtoOwner.set_id(owner.getId());
		dtoOwner.set_surname(owner.getSurname());
		_logger.info("Mapping domainOwner to dtoOwner done");
		return dtoOwner;
		
	}

	/**
	 * domainAccident to dtoAccident.
	 * @param accident
	 * @return
	 */
	static dtoAccident toDtoAccident(Accident accident){
		_logger.info("Mapping domainAccident to dtoAccident");
		dtoAccident dto = new dtoAccident();
		dto.setDescription(accident.getDescription());
		_logger.info("Mapping domainAccident to dtoAccident done");
		return dto;
		
	}
	
	/**
	 * domainAddress to dtoAddress.
	 * @param adress
	 * @return
	 */
	static dtoAddress toDtoAddress(Address adress){
		_logger.info("Mapping domainAddress to dtoAddress");
		dtoAddress dto =  new dtoAddress(adress.getStreetNumber(),adress.getStreet(),adress.getCity(),adress.getPostCode());
		dto.set_id(adress.getId());
		_logger.info("Mapping domainAddress to dtoAddress done");
		return dto;
		
	}
	
	/**
	 * domainHistory to dtoHistory.
	 * @param history
	 * @return
	 */
	static dtoHistory toDtoHistory(History history){
		_logger.info("Mapping domainHistory to dtoHistory");
		dtoHistory dto = new dtoHistory();
		dto.set_id(history.getId());
		dto.set_xmlId(history.getId()+"");
		dto.set_accident(toDtoAccident(history.getAccident()));
		_logger.info("Mapping domainHistory to dtoHistory done");
		return dto;
		
	}
	
	/**
	 * domainSecondHandVehicle to dtoSecondHandVehicle.
	 * @param domainSecond
	 * @return
	 */
	static dtoSecondHandVehicle toDtoSecondHandVehicle(SecondHandVehicle domainSecond){
		dtoSecondHandVehicle dto = new dtoSecondHandVehicle();
		dto.set_brand(domainSecond.get_brand());
		dto.set_id(domainSecond.get_id());
		dto.set_type(domainSecond.get_type());
		dto.set_year(domainSecond.get_year());
		dto.set_xmlId(domainSecond.get_id()+"");
		return dto;
	}
	
}
