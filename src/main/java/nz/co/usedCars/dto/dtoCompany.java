package nz.co.usedCars.dto;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author slee559
 *
 */
@XmlRootElement(name="dealer")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtoCompany {
	
	@XmlAttribute(name="company-id")
	private Long _id;

	@XmlElement(name="company-name")
	private String _name;

	@XmlElementWrapper(name="company-addresses")
	@XmlElement(name="company-address")
	private Set<dtoAddress> _address = new HashSet<dtoAddress>();
	
	public dtoCompany(){
		
	}	
	public dtoCompany(String _name ,Set<dtoAddress> address){
		this._name = _name;
		this._address = address;
	}
	
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public Set<dtoAddress> get_address() {
		return _address;
	}
	public void set_address(Set<dtoAddress> _address) {
		this._address = _address;
	}
	

}
