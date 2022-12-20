package com.kp.swasthik;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import java.io.*;

@Component
public class CxfProxyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("cxf:bean:mathServicePoxy?dataFormat=PAYLOAD")
		.doTry()
		.to("log:input")
		.to("cxf:bean:backendMathService?dataFormat=PAYLOAD")
		.to("log:output")
		  .doCatch(Exception.class)
            .transform().simple("${exception.message}") 
			.to("log:output")			
          .end();
	}

}
