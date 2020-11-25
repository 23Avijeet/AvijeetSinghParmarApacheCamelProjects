package com.cts;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;

public class CamelProducerConsumer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		CamelContext camel=new DefaultCamelContext();
		camel.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start")
				.process(new Processor() {

					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						System.out.println("Data Being processed from direct component to seda component");
						String message=exchange.getIn().getBody(String.class);
					message=message+"Happy Weekend";
					exchange.getOut().setBody(message.toUpperCase());
					}
				})
					.to("seda:end");
				}
			});
			
		
	

		camel.start();
		ProducerTemplate producer=camel.createProducerTemplate();
		producer.sendBody("direct:start","Hello Guys from Apache Camel");
			
		ConsumerTemplate consumer=camel.createConsumerTemplate();
		String message=consumer.receiveBody("seda:end",String.class);
		System.out.println(message);
	}
}
