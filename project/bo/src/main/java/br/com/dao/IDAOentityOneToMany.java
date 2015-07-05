package br.com.dao;

import spring.corp.framework.hibernate.dao.IGenericDAO;
import br.com.orm.ORM;
import br.com.orm.onetomany.EntityOneToMany;

public interface IDAOentityOneToMany extends IGenericDAO<EntityOneToMany> {

	public void insert(ORM orm);
	public ORM update(ORM orm);
	public void remove(ORM orm);
}