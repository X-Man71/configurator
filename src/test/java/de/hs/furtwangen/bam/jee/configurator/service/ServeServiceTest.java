package de.hs.furtwangen.bam.jee.configurator.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.OrderPositionRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.OrderPositionWeb;

@RunWith(MockitoJUnitRunner.class)
public class ServeServiceTest {
	
	@Mock
	private OrderPositionRepository orderPositionRepositoryMock;
	
	@InjectMocks
	private ServeService serveService;

	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetProductAsProvided() {
		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setComment("comment");
		orderPosition.setCreatedDate(LocalDateTime.now());
		orderPosition.setDone(false);
		orderPosition.setId(1L);
		orderPosition.setModifiedDate(LocalDateTime.now());
		orderPosition.setProvided(false);
		orderPosition.setRegistered(true);
		orderPosition.setVersion(1L);
		
		when(orderPositionRepositoryMock.save(orderPosition)).thenReturn(orderPosition);
		
		OrderPositionWeb orderPositionWeb = new OrderPositionWeb();
		orderPositionWeb.setId(1L);
		orderPositionWeb.setVersion(1L);
		
		OrderPosition orderPosition2 = serveService.save(orderPosition);
		Assert.assertEquals(false, orderPosition2.isProvided());
		
		verify(orderPositionRepositoryMock).save(orderPosition);
	}
	
}
