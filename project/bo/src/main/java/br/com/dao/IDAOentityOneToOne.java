package br.com.dao;

import spring.corp.framework.hibernate.dao.IGenericDAO;
import br.com.orm.ORM;
import br.com.orm.onetoone.EntityOneToOne;

public interface IDAOentityOneToOne extends IGenericDAO<EntityOneToOne> {

	public void insert(ORM orm);
	public ORM update(ORM orm);
	public void remove(ORM orm);
}