package br.com.igorcarvalhodev.springbootws.models.abstracts;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.igorcarvalhodev.springbootws.models.abstracts.interfaces.IModel;

@MappedSuperclass
public abstract class AbstractModel implements IModel {

	private static final long serialVersionUID = -8566289238146888934L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractModel other = (AbstractModel) obj;
		return Objects.equals(id, other.id);
	}


}
