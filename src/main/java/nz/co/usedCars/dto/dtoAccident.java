package nz.co.usedCars.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author slee559
 *
 */
@XmlRootElement(name="accident")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtoAccident {
	
	@XmlElement(name="description")
	private String _description;

	public dtoAccident(){
	}
	
	public dtoAccident(String description) {
		this._description = description;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}

}
