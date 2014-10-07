package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.OrderPositionRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.ProductRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.TableCustomerRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.OrderPositionModel;

@Service
@Transactional
public class ServeService {

	@Autowired
	private TableCustomerRepository tableCustomerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderPositionRepository orderPositionRepository;

	@Transactional(readOnly = true)
	public Iterable<TableCustomer> findAllTableCustomer() {
		return tableCustomerRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Iterable<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Product findOneProduct(Long productId) {
		return productRepository.findOne(productId);
	}

	@Transactional(readOnly = true)
	public TableCustomer findOneTableCustomer(Long tableCustomerId) {
		return tableCustomerRepository.findOne(tableCustomerId);
	}

	@Transactional(readOnly = true)
	public User findOneUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public void saveOrderPosition(Long productId, Long tableId) {
		OrderPosition orderPosition = new OrderPosition();

		orderPosition.setDone(false);
		orderPosition.setProvided(false);
		orderPosition.setRegistered(false);

		Product product = findOneProduct(productId);
		product.addOrderPosition(orderPosition);

		orderPosition.setProduct(product);
		orderPosition.setTableCustomer(findOneTableCustomer(tableId));

		orderPosition.setUser(getCurrentUser());

		orderPositionRepository.save(orderPosition);

		for (OrderPosition position : orderPositionRepository.findAll()) {
			System.out.println(position.getCreatedDate() + " "
					+ position.getModifiedDate() + " " + position.getComment()
					+ " " + position.getProduct().getProductname());
		}
	}

	@Transactional
	public void deleteOrderPosition(Long productId, Long tableId) {
		orderPositionRepository
				.deleteByTableCustomerAndProductAndRegisteredFalse(
						findOneTableCustomer(tableId),
						findOneProduct(productId));
	}

	@Transactional(readOnly = true)
	public Iterable<OrderPosition> findByTableCustomerAndDoneFalse(Long tableId) {
		return orderPositionRepository
				.findByTableCustomerAndDoneFalse(findOneTableCustomer(tableId));
	}

	@Transactional
	public Iterable<OrderPosition> submitOrderFromTable(Long tableId) {
		boolean registered = true;

		Iterable<OrderPosition> orderPositionList = orderPositionRepository
				.findByTableCustomerAndRegisteredFalse(tableCustomerRepository
						.findOne(tableId));

		for (OrderPosition orderPosition : orderPositionList) {
			orderPosition.setRegistered(registered);
		}
		return orderPositionRepository.save(orderPositionList);
	}
	
	@Transactional
	public Iterable<OrderPosition> submitOrderFromTableWithComment(Long tableId, OrderPositionModel orderPositionModel)
	{
		boolean registered = true;
		
		Iterable<OrderPosition> orderPositionList = orderPositionRepository
				.findByTableCustomerAndRegisteredFalse(tableCustomerRepository
						.findOne(tableId));
		
		HashMap<Long, String> commentMap = new HashMap<Long, String>();
		
		for(OrderPosition orderPositionWeb : orderPositionModel.getListOrderPositions())
		{
			commentMap.put(orderPositionWeb.getId(), orderPositionWeb.getComment());			
		}
		
		for (OrderPosition orderPosition : orderPositionList) {
			orderPosition.setRegistered(registered);			
			orderPosition.setComment(commentMap.get(orderPosition.getId()));
		}
		
		return orderPositionRepository.save(orderPositionList);
		
	}

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		return findOneUser(name);
	}

	@Transactional(readOnly = true)
	public Integer countByTableCustomerAndProductAndRegisteredFalse(
			Long tableCustomerId, Long productId) {

		return orderPositionRepository
				.countByTableCustomerAndProductAndRegisteredFalse(
						tableCustomerRepository.findOne(tableCustomerId),
						productRepository.findOne(productId));
	}
	
	@Transactional(readOnly = true)
	public Iterable<OrderPosition> findByTableCustomerAndRegisteredFalse(Long tableCustomerId)
	{
		return orderPositionRepository
				.findByTableCustomerAndRegisteredFalse(tableCustomerRepository
						.findOne(tableCustomerId));
	}
	
	@Transactional
	public void deleteOrderPosition(OrderPosition orderPosition){
		orderPositionRepository.delete(orderPosition.getId());
	}
	
	@Transactional(readOnly = true)
	public Iterable<OrderPosition> findByRegisteredTrueOrderByIdDesc(){
		//TODO When User has Role cook get Food
		//TODO When User has Role Barkeeper getDrinks
		return orderPositionRepository.findByRegisteredTrueOrderByIdDesc();
	}
	
	@Transactional(readOnly = true)
	public Iterable<OrderPosition> findByRegisteredTrueAndIdGreaterThanOrderByIdDesc(Long id){
		return orderPositionRepository.findByRegisteredTrueAndIdGreaterThanOrderByIdAsc(id);
	}

}
