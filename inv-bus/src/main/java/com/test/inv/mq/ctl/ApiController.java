package com.test.inv.mq.ctl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.rabbitmq.RabbitConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ApiController {


    @Autowired
    private  RabbitTemplate rabbitTemplate;

	@GetMapping(value = "/send/{message}")
	@ApiOperation(value = "Send Message")
	public String send(
			@ApiParam(name = "message", value = "message", defaultValue = "richard....")
			@PathVariable String message) {	
		rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_QUEUE,message);
		rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_QUEUE,message);

		return "Success";
	

	}
}