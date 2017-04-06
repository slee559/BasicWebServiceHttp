package nz.co.usedCars.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author slee559
 *
 */
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtoAddress {
	
	@XmlAttribute(name="address-id")
	private Long _id;
	
	@XmlElement(name="streetNumber")
	private String _streetNumber;
	
	public void set_postCode(int _postCode) {
		this._postCode = _postCode;
	}
	@XmlElement(name="street")
	private String _street;
	
	@XmlElement(name="city")
	private String _city;
	
	@XmlElement(name="postCode")
	private int _postCode;
	
	public dtoAddress() {
		
	}
	
	public dtoAddress(String streetNumber, String street, String city, int postCode) {
		_streetNumber = streetNumber;
		_street = street;
		_city = city;
		_postCode = postCode;
	}
	public dtoAddress(String streetNumber, String street, String city, int postCode, Long _id) {
		_streetNumber = streetNumber;
		_street = street;
		_city = city;
		_postCode = postCode;
		this._id = _id;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String get_streetNumber() {
		return _streetNumber;
	}

	public void set_streetNumber(String _streetNumber) {
		this._streetNumber = _streetNumber;
	}

	public String get_street() {
		return _street;
	}

	public void set_street(String _street) {
		this._street = _street;
	}

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}

	public int get_postCode() {
		return _postCode;
	}


}
