package org.example.hrp.camel.processors;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.hrp.beans.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Viswa Ramamoorthy
 *
 * Class to inspect headers and prepare target URL for destination
 */
public class RequestProcessor implements Processor {
	
	private Logger logger = LoggerFactory.getLogger(RequestProcessor.class);

	public void process(Exchange exchange) throws Exception {
		logger.trace("process enter >>>");
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String targetUrl = constructTargetUri(headers);

		exchange.getIn().setHeader("targetUrl", targetUrl);

		logger.debug("targetUrl in pre-process {}", targetUrl);
		logger.trace("process exit <<<");
	}
	
	private String constructTargetUri(Map<String, Object> headers) {
		String targetUri = "";
		List<Object> arguments = new ArrayList<Object>();
		targetUri = Constants.LIST_TARGET_URI;
		logger.debug("targetUri {}", targetUri);
		String bridgeParamValue = (String)headers.get(Constants.bridgeEndpointParam);
		if (bridgeParamValue == null) {
			bridgeParamValue = "false";
		}
		arguments.add(bridgeParamValue);
		
		return MessageFormat.format(targetUri, arguments.toArray());
	}
}
