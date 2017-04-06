package nz.co.usedCars.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author slee559
 *
 */
@XmlRootElement(name = "history")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtoHistory {
	
	@XmlID     
	@XmlAttribute(name="history-xml-id")
	private String _xmlId;  
	
	@XmlAttribute(name="history-id")
	private Long _id;
	
	@XmlElement(name="history-owner")
	private dtoOwner _owner;
	
	@XmlElement(name="accident")
	private dtoAccident _accident;
	
	public dtoHistory(){
		
	}
		
	public dtoHistory(dtoAccident accident,dtoOwner owner) {
		this._owner = owner;
		this._accident = accident;
	}
	
	public String get_xmlId() {
		return _xmlId;
	}

	public void set_xmlId(String _xmlId) {
		this._xmlId = getClass( ).getName( )+_xmlId;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public dtoOwner get_owner() {
		return _owner;
	}

	public void set_owner(dtoOwner _owner) {
		this._owner = _owner;
	}

	public dtoAccident get_accident() {
		return _accident;
	}

	public void set_accident(dtoAccident _accident) {
		this._accident = _accident;
	}

	

}
