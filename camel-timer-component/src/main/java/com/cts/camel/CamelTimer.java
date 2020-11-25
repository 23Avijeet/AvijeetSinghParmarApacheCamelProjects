package com.cts.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelTimer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
CamelContext camel=new DefaultCamelContext();
camel.addRoutes(new RouteBuilder() {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer:myTimer?period=1000").setBody(simple("Hello FromCamel At ${header.firedTime}"))
		.to("stream:out")
		.bean(new MyBean(),"sayHello");
	}
	

	});
camel.start();
Thread.sleep(5000);
camel.stop();
	}

}
