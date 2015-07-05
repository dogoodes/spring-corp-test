package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import spring.corp.framework.hibernate.dao.GenericDAO;
import br.com.orm.ORM;

public class DAOorm extends GenericDAO<ORM> implements IDAOorm {

	private EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public void insert(ORM orm) {
		super.insert(orm);
	}
	
	@Override
	public ORM update(ORM orm) {
		return super.update(orm);
	}
	
	@Override
	public void remove(ORM orm) {
		super.remove(orm);
	}
}