package nz.co.usedCars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author slee559
 *
 */
@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SECONDHAND")
public class SecondHandVehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long _id;

	@Column(name="YEAR")
	protected String _year;
	
	@Column(name="BRAND")
	protected String _brand;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE", nullable=false)
	private CarType _type;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="VEHICLE_OWNER_ID",
			joinColumns = @JoinColumn(name="VEHICLE_ID"),
			inverseJoinColumns = @JoinColumn(name="OWNER_ID"))
	protected Owner _ownerVehicle;
	
	public SecondHandVehicle(){}
	
	public SecondHandVehicle(String _year,String _brand,CarType _type, Owner _ownerVehicle){
		this._year = _year;
		this._brand = _brand;
		this._type = _type;
		this._ownerVehicle = _ownerVehicle;
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

	public CarType get_type() {
		return _type;
	}

	public void set_type(CarType _type) {
		this._type = _type;
	}

	public Owner get_owner() {
		return _ownerVehicle;
	}

	public void set_owner(Owner _owner) {
		this._ownerVehicle = _owner;
	}

	public Long get_id() {
		return _id;
	}

	
	



}
