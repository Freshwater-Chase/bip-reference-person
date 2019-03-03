package gov.va.ocp.reference.starter.feign.autoconfigure;

import java.io.IOException;
import java.io.Reader;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import feign.Response;
import feign.codec.ErrorDecoder;
import gov.va.ocp.reference.framework.exception.OcpFeignRuntimeException;
import gov.va.ocp.reference.framework.messages.HttpStatusForMessage;
import gov.va.ocp.reference.framework.messages.MessageSeverity;
import gov.va.ocp.reference.framework.service.DomainResponse;

public class FeignCustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
        	
        	StringBuffer strBuffer = new StringBuffer();
        	try {
        		
				Reader inputReader = response.body().asReader();
				int data = inputReader.read();
				while(data != -1) {
				   strBuffer.append((char)data);
				   data = inputReader.read();
			    }
				
			} catch (IOException e) {
				 return defaultErrorDecoder.decode(methodKey, response);
			}

        	DomainResponse domainResponse = new DomainResponse();
        	domainResponse.setDoNotCacheResponse(true);
        	//domainResponse.addMessage(messageObject.getString("severity"), messageObject.getString("key"), 
        	//		messageObject.getString("text"), messageObject.getString("status"));
        	try {
                JSONObject messageObjects = new JSONObject(strBuffer.toString());
                JSONArray jsonarray = messageObjects.getJSONArray("messages");
                JSONObject messageObject = jsonarray.getJSONObject(0);
				domainResponse.addMessage(MessageSeverity.FATAL, messageObject.getString("key"), 
				    		messageObject.getString("text"), HttpStatusForMessage.SERVICE_UNAVAILABLE);
			} catch (JSONException e) {
				return defaultErrorDecoder.decode(methodKey, response);
			}
            return new OcpFeignRuntimeException(domainResponse);
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

}