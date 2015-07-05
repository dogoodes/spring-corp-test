package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import spring.corp.framework.hibernate.dao.GenericDAO;
import br.com.orm.ORM;
import br.com.orm.onetomany.EntityOneToMany;

public class DAOentityOneToMany extends GenericDAO<EntityOneToMany> implements IDAOentityOneToMany {

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
	public void insert(EntityOneToMany orm) {
		super.insert(orm);
	}
	
	@Override
	public EntityOneToMany update(EntityOneToMany orm) {
		return super.update(orm);
	}
	
	@Override
	public void remove(EntityOneToMany orm) {
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