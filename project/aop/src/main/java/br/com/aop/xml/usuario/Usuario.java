package br.com.aop.xml.usuario;

import java.io.Serializable;

import spring.corp.framework.audit.IAuditable;
import spring.corp.framework.utils.UUIDUtils;

public class Usuario implements IAuditable, Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nome;
	
	public Usuario(){}
	public Usuario(String codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}
	
	@Override
	public String getUuid() {
		return UUIDUtils.generateUUID();
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + "]";
	}
}