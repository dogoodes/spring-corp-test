package br.com.bo;

import br.com.orm.ORM;
import br.com.orm.onetomany.EntityOneToMany;
import br.com.orm.onetoone.EntityOneToOne;

public interface IBOorm {

	public void inserir(ORM orm);
	public ORM alterar(ORM orm);
	public void excluir(Long id);
	public void excluir(ORM orm);
	
	public void inserir(EntityOneToOne orm);
	
	public void inserir(EntityOneToMany orm);
}