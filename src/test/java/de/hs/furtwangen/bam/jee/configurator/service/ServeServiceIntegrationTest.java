package de.hs.furtwangen.bam.jee.configurator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/business-config.xml")
public class ServeServiceIntegrationTest {
	
	@Autowired
	private ServeService serveService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=ObjectOptimisticLockingFailureException.class)
	public void testSetProductAsProvidedOptimisticLock()
	{
		OrderPosition orderPosition = new OrderPosition();		
		long orderPositionId = serveService.save(orderPosition).getId();
		
		OrderPosition orderPosition1 = serveService.findOneOrderPosition(orderPositionId);
		OrderPosition orderPosition2 = serveService.findOneOrderPosition(orderPositionId);
		
		orderPosition1.setDone(true);
		serveService.save(orderPosition1);
		
		orderPosition2.setRegistered(true);
		serveService.save(orderPosition2);
	}

}
