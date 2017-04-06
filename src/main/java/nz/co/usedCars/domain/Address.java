package nz.co.usedCars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author slee559
 *
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long _id;
	
	@Column(name = "STREET_NUMBER", length = 10)
	private String _streetNumber;
	
	@Column(name = "STREET", length = 50)
	private String _street;
	
	@Column(name = "CITY", length = 30)
	private String _city;
	
	@Column(name = "POST_CODE", length = 10)
	private int _postCode;
	
	public Address() {}
	
	
	public Long getId() {
		return _id;
	}

	public String getStreetNumber() {
		return _streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this._streetNumber = streetNumber;
	}

	public String getStreet() {
		return _street;
	}

	public void setStreet(String street) {
		this._street = street;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		this._city = city;
	}

	public int getPostCode() {
		return _postCode;
	}

	public void setPostCode(int postCode) {
		this._postCode = postCode;
	}
}
