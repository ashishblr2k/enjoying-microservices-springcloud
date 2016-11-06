package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.repository.OrderRepository;

@RestController
@ConfigurationProperties(prefix="newConfig")
@RefreshScope()

public class OrderAPI {
	private String data;
	
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public OrderRepository getManufacturerRepository() {
		return manufacturerRepository;
	}

	public void setManufacturerRepository(OrderRepository manufacturerRepository) {
		this.manufacturerRepository = manufacturerRepository;
	}

	@Autowired
	private OrderRepository manufacturerRepository;
	
	
	
	@RequestMapping(value="/api/order",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> add(@RequestBody Order manufacturer){
		manufacturerRepository.save(manufacturer);
		return new ResponseEntity<Order>(manufacturer, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/order/{id}",method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> update(@RequestBody Order manufacturer,
			@PathVariable("id")Integer id){
		Order manufacturerOne=manufacturerRepository.findOne(id);
		if(manufacturerOne!=null){
			manufacturerOne.setActive(manufacturer.getActive());
			manufacturerOne.setOrderDate(manufacturer.getOrderDate());
			manufacturerOne.setName(manufacturer.getName());
			manufacturerRepository.save(manufacturerOne);	
		}
		
		return new ResponseEntity<Order>(manufacturer, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/api/order",method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public ResponseEntity<List<Order>> findAll(){
		List<Order> list=new ArrayList<>();
		list.add(new Order(1,data,new Date(),true));
	return new ResponseEntity<List<Order>>(list, 
			HttpStatus.OK);
}


}
