package com.travel.agent.test.dao;

import java.util.Set;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IStateMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.StateMaster;
import com.travel.agent.model.StateMaster.StateMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
public class StateMasterDaoTest {

	@Autowired
	private IStateMasterDao iStateMasterDao;
	
	public static String junkString = "gugiuhdgugiuhduiyhshdxiuhx9wyshx9ydhci8dwygcuygdwkuchoudwychgdiuwhciudwhciuhdwuigciuwdgciudwgcigdwycgudyw ciuhdwcoiihwdoihciouwdhciukhgwdiycgiwdchpnwdic[0dwicoidwhciuwdgcgdwugcuywgdcdiuy9q7d98q87gibiubxy8ug8yhx0ju9yd897e2ydhioeqndiehwbiuiwhed9uhwecibx2eiugc9ewhd0ie2hjc9doeh2ouchweiuhcbiuw2bc"
+ "uiyhshdxiuhx9wyshx9ydhci8dwygcuygdwkuchoudwychgdiuwhciudwhciuhdwuigciuwdgciudwgcigdwycgudyw ciuhdwcoiihwdoihciouwdhciukhgwdiycgiwdchpnwdic[0dwicoidwhciuwdgcgdwugcuywgdcdiuy9q7d98q";

	@Test
	public void should2GetAll() throws TASystemException {
		Set<StateMaster> set = iStateMasterDao.findAll();
		Assert.assertEquals(5, set.size());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void should1Create() throws TASystemException {
		StateMaster stateMaster = new StateMaster();
		StateMasterBuilder smb = stateMaster.new StateMasterBuilder();
		stateMaster = smb
				.stateCode("NJ")
				.stateName("New Jersey")
				.unionTerritory(false)
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST)
				.buildNew();
		StateMaster created = iStateMasterDao.createEntity(stateMaster);
		Assert.assertEquals(created, stateMaster);
	}
	
	@Test(expected=TASystemException.class)
	@Transactional(propagation = Propagation.REQUIRED)
	public void should10NotCreate() throws TASystemException {
		StateMaster stateMaster = new StateMaster();
		StateMasterBuilder smb = stateMaster.new StateMasterBuilder();
		stateMaster = smb
				.stateCode("NJ")
				.stateName(junkString)
				.unionTerritory(false)
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST)
				.buildNew();
		StateMaster created = iStateMasterDao.createEntity(stateMaster);
		Assert.assertEquals(created, stateMaster);
	}
	
	
	@Test
	public void should3GetByID() throws TASystemException {
		StateMaster sm = iStateMasterDao.findById("1");
		Assert.assertEquals("Andhra Pradesh", sm.getStateName());
	}
	
	@Test
	public void should4NotGetByID() throws TASystemException {
		StateMaster sm1 = iStateMasterDao.findById("1087gbighiu98u8y87gh9uh");
		Assert.assertNull(sm1);
	}

	@Test
	public void should5GetStateAndLocationByID() throws TASystemException {
		StateMaster sm = iStateMasterDao.getStateAndLocation("1");
		Assert.assertEquals("AP", sm.getStateCode());
		Assert.assertNotNull(sm.getLocationMasterSet());
	}
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void should6Update() throws TASystemException {
		StateMaster sm = iStateMasterDao.findById("1");
		StateMasterBuilder smb = sm.new StateMasterBuilder(sm);
		sm = smb.stateCode("J&K").update();
		StateMaster smUpdated2 = iStateMasterDao.updateEntity(sm);
		Assert.assertEquals(sm.getStateMasterID(), smUpdated2.getStateMasterID());
		Assert.assertEquals("J&K", smUpdated2.getStateCode());
	}


}
