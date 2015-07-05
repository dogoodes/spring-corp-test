package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import spring.corp.framework.hibernate.dao.GenericDAO;
import br.com.orm.ORM;
import br.com.orm.onetoone.EntityOneToOne;

public class DAOentityOneToOne extends GenericDAO<EntityOneToOne> implements IDAOentityOneToOne {

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
	public void insert(EntityOneToOne orm) {
		super.insert(orm);
	}
	
	@Override
	public EntityOneToOne update(EntityOneToOne orm) {
		return super.update(orm);
	}
	
	@Override
	public void remove(EntityOneToOne orm) {
		super.remove(orm);
	}

	@Override
	public void insert(ORM orm) {
		em.persist(orm);
	}

	@Override
	public ORM update(ORM orm) {
		return em.merge(orm);
	}

	@Override
	public void remove(ORM orm) {
		em.remove(orm);
	}
}