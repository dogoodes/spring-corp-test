package br.com.orm.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.orm.ORM;

@Entity
@Table(name = "TABELA_ENTIDADE_ONE_TO_ONE")
public class EntityOneToOne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="uuid", nullable=false)
	private String uuid;

	@Column(name="nome")
	private String nome;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="TABELA_ENTIDADE_ONE_TO_ONE_ORM", joinColumns={@JoinColumn(name="ID_ENTIDADE_ONE_TO_ONE")}, inverseJoinColumns={@JoinColumn(name="ID_ORM")})
	private ORM orm;

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

	public ORM getOrm() {
		return orm;
	}

	public void setOrm(ORM orm) {
		this.orm = orm;
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
		EntityOneToOne other = (EntityOneToOne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntityOneToOne [id=" + id + ", uuid=" + uuid + ", nome=" + nome + ", orm=" + (orm == null ? "null" : orm.toString()) + "]";
	}
}