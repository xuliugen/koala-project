package org.openkoala.organisation.application.impl;

import org.openkoala.organisation.application.BaseApplication;
import org.openkoala.organisation.domain.OrganizationAbstractEntity;
import org.openkoala.organisation.domain.Party;
import org.springframework.transaction.annotation.Transactional;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Named
@Transactional(value="transactionManager_org")
@Interceptors(value = org.openkoala.koala.util.SpringEJBIntercepter.class)
@Stateless(name = "BaseApplication")
@Remote
public class BaseApplicationImpl implements BaseApplication {

	@Override
	public void saveParty(Party party) {
		party.save();
	}

	@Override
	public void updateParty(Party party) {
		party.save();
	}

	@Override
	public void terminateParty(Party party) {
		party.terminate(new Date());
	}

	@Override
	public <T extends OrganizationAbstractEntity> T getEntity(Class<T> clazz, Long id) {
		return OrganizationAbstractEntity.get(clazz, id);
	}

	@Override
	public <T extends Party> void terminateParties(Set<T> parties) {
		for (T party : parties) {
			party.terminate(new Date());
		}
	}

	@Override
	public <T extends Party> List<T> findAll(Class<T> clazz) {
		return T.findAll(clazz, new Date());
	}

}
