package nz.co.usedCars.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author slee559
 *
 */
@Entity
@Table(name = "COMPANY")
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long _id;

	@Column(name="COMPANY_NAME")
	private String _name;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(
			name="COMPANY_ADDRESS_ID")
	private Set<Address> _address = new HashSet<Address>();
	
	public Company(){}
	
	public Company(String _name, Set<Address> _address){
		this._name = _name;
		this._address = _address;
	}
	
	public Long get_id() {
		return _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public Set<Address> get_address() {
		return _address;
	}

	public void set_address(Set<Address> _address) {
		this._address = _address;
	}

	public void add_address(Address _address){
		this._address.add(_address);
	}

	
}
