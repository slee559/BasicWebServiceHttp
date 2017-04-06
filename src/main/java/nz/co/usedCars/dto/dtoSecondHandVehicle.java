package nz.co.usedCars.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import nz.co.usedCars.domain.CarType;
import nz.co.usedCars.dto.dtoOwner;

/**
 * 
 * @author slee559
 *
 */
@XmlRootElement(name="secondhand")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtoSecondHandVehicle {
	
	@XmlID     
	@XmlAttribute(name="vehicle-xml-id")
	private String _xmlId;  
	

	@XmlAttribute(name="vehicle-id")
	protected Long _id;
	
	@XmlElement(name="vehicle-year")
	protected String _year;
	
	@XmlElement(name="vehicle-brand")
	protected String _brand;
	
	
	@XmlElement(name="vehicle-owner")
	protected dtoOwner _owner;
	
	@XmlElement(name="vehicle-type")
	private CarType _type;

	public dtoSecondHandVehicle(){
		
	}
	
	public dtoSecondHandVehicle(String _year, String _brand, dtoOwner _owner, CarType _type ){
		this._year = _year;
		this._brand = _brand;
		this._owner = _owner;
		this._type = _type;
	}
	
	public String get_xmlId() {
		return _xmlId;
	}

	public void set_xmlId(String _xmlId) {
		 this._xmlId = getClass( ).getName( ) + _xmlId; 
	}
	
	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String get_year() {
		return _year;
	}

	public void set_year(String _year) {
		this._year = _year;
	}

	public String get_brand() {
		return _brand;
	}

	public void set_brand(String _brand) {
		this._brand = _brand;
	}

	public dtoOwner get_owner() {
		return _owner;
	}

	public void set_owner(dtoOwner _owner) {
		this._owner = _owner;
	}

	public CarType get_type() {
		return _type;
	}

	public void set_type(CarType _type) {
		this._type = _type;
	}
	
	
}
