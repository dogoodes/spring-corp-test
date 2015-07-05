package br.com.orm.onetomany;

import java.util.ArrayList;
import java.util.Collection;

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
import javax.persistence.Table;

import br.com.orm.ORM;

@Entity
@Table(name = "TABELA_ENTIDADE_ONE_TO_MANY")
public class EntityOneToMany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="uuid", nullable=false)
	private String uuid;

	@Column(name="nome")
	private String nome;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="TABELA_ENTIDADE_ONE_TO_MANY_ORM", joinColumns={@JoinColumn(name="ID_ENTIDADE_ONE_TO_MANY")}, inverseJoinColumns={@JoinColumn(name="ID_ORM")})
	private Collection<ORM> orms = new ArrayList<ORM>();

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<ORM> getOrms() {
		return orms;
	}

	public void setOrms(Collection<ORM> orms) {
		this.orms = orms;
	}
	
	public void addOrm(ORM orm) {
		if (orms == null) {
			orms = new ArrayList<ORM>();
		}
		this.orms.add(orm);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityOneToMany other = (EntityOneToMany) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntityOneToMany [id=" + id + ", uuid=" + uuid + ", nome=" + nome + ", orms=" + orms + "]";
	}
}