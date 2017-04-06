package nz.co.usedCars.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author slee559
 *
 */
@XmlRootElement(name="owner")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtoOwner {
	@XmlAttribute(name="owner-id")
	private Long _id;

	@XmlElement(name="owner-surname")
	private String _surname;
	
	@XmlElement(name="owner-firstname")
	private String _firstname;
	
	@XmlIDREF
	@XmlElement(name="owner-history")
	private Set<dtoHistory> _history = new HashSet<dtoHistory>();
	
	@XmlElementWrapper(name="owner-companies")
	@XmlElement(name="owner-comapny")
	private Set<dtoCompany> _companies = new HashSet<dtoCompany>();
	
	@XmlIDREF
	@XmlElement(name="owner-vehicle")
	private List<dtoSecondHandVehicle> _vehicle = new ArrayList<dtoSecondHandVehicle>();
	
	@XmlElement(name="owner-adress")
	private dtoAddress _adress;
	
	public dtoOwner(){
	
	}

	public dtoOwner(String surname, String firstname, HashSet<dtoHistory> history, dtoAddress _adress,Set<dtoCompany> _companies,List<dtoSecondHandVehicle> _vehicle) {
		this._surname = surname;
		this._firstname = firstname;
		this._history = history;
		this._adress = _adress;
		this._companies = _companies;
		this._vehicle= _vehicle;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String get_surname() {
		return _surname;
	}

	public void set_surname(String _surname) {
		this._surname = _surname;
	}

	public String get_firstname() {
		return _firstname;
	}

	public void set_firstname(String _firstname) {
		this._firstname = _firstname;
	}

	public Set<dtoHistory> get_history() {
		return _history;
	}

	public void set_history(Set<dtoHistory> _history) {
		this._history = _history;
	}

	public Set<dtoCompany> get_companies() {
		return _companies;
	}

	public void set_companies(Set<dtoCompany> _companies) {
		this._companies = _companies;
	}

	public List<dtoSecondHandVehicle> get_vehicle() {
		return _vehicle;
	}

	public void set_vehicle(List<dtoSecondHandVehicle> _vehicle) {
		this._vehicle = _vehicle;
	}

	public dtoAddress get_adress() {
		return _adress;
	}

	public void set_adress(dtoAddress _adress) {
		this._adress = _adress;
	}
}
