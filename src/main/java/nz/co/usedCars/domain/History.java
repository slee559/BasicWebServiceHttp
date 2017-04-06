package nz.co.usedCars.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author slee559
 *
 */
@Entity
@Table(name = "HISTORY")
public class History {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long _id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OWNER_HISTORY_ID")
	private Owner _ownerHistory;
	
	@Embedded
	private Accident _accident;
	
	public History(){}
	
	public Accident getAccident() {
		return _accident;
	}

	public void setAccident(Accident accident) {
		this._accident = accident;
	}

	public Long getId() {
		return _id;
	}
	
	public Owner getOwner() {
		return _ownerHistory;
	}

	public void setOwner(Owner owner) {
		this._ownerHistory = owner;
	}


}
