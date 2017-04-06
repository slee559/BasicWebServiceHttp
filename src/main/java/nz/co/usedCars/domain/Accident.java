package nz.co.usedCars.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author slee559
 *
 */
@Embeddable
public class Accident {
	
	@Column(name="DESCRIPTION")
	private String _description;

	public Accident(){}
	
	public Accident(String _description){
		this._description = _description;
	}
	
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}
	
}
