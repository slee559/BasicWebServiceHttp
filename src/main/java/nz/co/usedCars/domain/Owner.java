package nz.co.usedCars.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author slee559
 *
 */
@Entity
@Table(name = "OWNER")
public class Owner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long _id;

	@Column(name="OWNER_SURNAME")
	private String _surname;
	
	@Column(name="OWNER_FIRSTNAME")
	private String _firstname;
	
	@OneToMany(mappedBy = "_ownerHistory", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<History> _history = new HashSet<History>();
	
	@OneToMany(mappedBy = "_ownerVehicle", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<SecondHandVehicle> _vehicle = new ArrayList<SecondHandVehicle>();
	

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinTable(
			name="OWNER_ADDRESS",
			joinColumns =
				@JoinColumn(name="OWNER_ID"),
			inverseJoinColumns=
				@JoinColumn(name="ADDRESS_ID",
							unique=true))
	private Address _adress;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="COMPANIES_ID")		
	private Set<Company> _companies = new HashSet<Company>();
	
	public Owner(){}
		
	public Long getId() {
		return _id;
	}

	public String getSurname() {
		return _surname;
	}

	public void setSurname(String surname) {
		this._surname = surname;
	}

	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		this._firstname = firstname;
	}

	public Set<History> getHistory() {
		return _history;
	}

	public void addHistory(History history) {
		_history.add(history);
	}
	public Address getAdress() {
		return _adress;
	}

	public void setAdress(Address adress) {
		this._adress = adress;
	}

	public List<SecondHandVehicle> getVehicle() {
		return _vehicle;
	}

	public void addVehicle(SecondHandVehicle vehicle) {
		_vehicle.add(vehicle);
	}
	public void set_vehicle(List<SecondHandVehicle> _vehicle) {
		this._vehicle = _vehicle;
	}
	

	public Set<Company> getComapnies() {
		return _companies;
	}

	public void Comapny(Company dealers) {
		this._companies.add(dealers);
	}
	public void setComapnies(Set<Company> company) {
		_companies =company;
	}

}
