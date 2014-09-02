package de.hs.furtwangen.bam.jee.configurator.service;

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
import de.hs.furtwangen.bam.jee.configurator.web.domain.ProductAmountAndComment;

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
	public Iterable<Product> findAllProduct(){		
		return productRepository.findAll();
	}
	
	@Transactional(readOnly= true)
	public Product findOneProduct(Long productId){
		return productRepository.findOne(productId);
	}
	
	@Transactional(readOnly= true)
	public TableCustomer findOneTableCustomer(Long tableCustomerId){
		return tableCustomerRepository.findOne(tableCustomerId);
	}
	
	@Transactional(readOnly=true)
	public User findOneUser(String username){
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void saveOrderPosition(Long productId, Long tableId,ProductAmountAndComment productAmountAndComment){		
		for(int i = 1;i <= productAmountAndComment.getAmount();i++)
		{
			OrderPosition orderPosition = new OrderPosition();
			
			if(productAmountAndComment.isForall())
			{
				orderPosition.setComment(productAmountAndComment.getComment());
			}
			else
			{
				if(i==1)
				{
					orderPosition.setComment(productAmountAndComment.getComment());
				}
			}
			orderPosition.setDone(false);	
			orderPosition.setProvided(false);		
		
			Product product = findOneProduct(productId);
			product.addOrderPosition(orderPosition);
		
			orderPosition.setProduct(product);
			orderPosition.setTableCustomer(findOneTableCustomer(tableId));		
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			orderPosition.setUser(findOneUser(name));
		
			orderPositionRepository.save(orderPosition);
		}
		
		for(OrderPosition position : orderPositionRepository.findAll()){
			System.out.println(position.getCreatedDate()+" "+position.getModifiedDate()+" "+position.getComment()+" "+position.getProduct().getProductname());
		}
	}

}
