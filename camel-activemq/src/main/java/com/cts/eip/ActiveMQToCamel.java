package com.cts.eip;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;


public class ActiveMQToCamel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext camel=new DefaultCamelContext();
		ConnectionFactory factory = new ActiveMQConnectionFactory();
		camel.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(factory));
		camel.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start")
				.to("activemq:queue:my_queue").to("stream:out");
				
			}
			

			});
		camel.start();
		Thread.sleep(5000);
		camel.stop();
	}

}
