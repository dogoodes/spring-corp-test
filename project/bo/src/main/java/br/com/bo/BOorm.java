package br.com.bo;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.corp.framework.log.ManagerLog;
import br.com.dao.IDAOentityOneToMany;
import br.com.dao.IDAOentityOneToOne;
import br.com.dao.IDAOorm;
import br.com.orm.ORM;
import br.com.orm.onetomany.EntityOneToMany;
import br.com.orm.onetoone.EntityOneToOne;

public class BOorm implements IBOorm {

	private IDAOorm daoorm;
	private IDAOentityOneToOne daoentityOneToOne;
	private IDAOentityOneToMany daoentityOneToMany;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void inserir(ORM orm) {
		daoorm.insert(orm);
		ManagerLog.debug(BOorm.class, "Inclus\u00e3o : " + orm.toString());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ORM alterar(ORM orm) {
		return daoorm.update(orm);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(Long id) {
		//daoorm.remove(orm);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(ORM orm) {
		daoorm.remove(orm);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void inserir(EntityOneToOne orm) {
		daoentityOneToOne.insert(orm);
		ManagerLog.debug(BOorm.class, "Inclus\u00e3o : " + orm.toString());
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void inserir(EntityOneToMany orm) {
		daoentityOneToMany.insert(orm);
		ManagerLog.debug(BOorm.class, "Inclus\u00e3o : " + orm.toString());
	}

	public IDAOorm getDaoorm() {
		return daoorm;
	}

	public void setDaoorm(IDAOorm dao) {
		this.daoorm = dao;
	}

	public IDAOentityOneToOne getDaoentityOneToOne() {
		return daoentityOneToOne;
	}

	public void setDaoentityOneToOne(IDAOentityOneToOne daoentityOneToOne) {
		this.daoentityOneToOne = daoentityOneToOne;
	}

	public IDAOentityOneToMany getDaoentityOneToMany() {
		return daoentityOneToMany;
	}

	public void setDaoentityOneToMany(IDAOentityOneToMany daoentityOneToMany) {
		this.daoentityOneToMany = daoentityOneToMany;
	}
}