package gov.va.ocp.reference.framework.audit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import gov.va.ocp.reference.framework.audit.AuditEvents;
import gov.va.ocp.reference.framework.audit.Auditable;
import gov.va.ocp.reference.framework.messages.MessageSeverity;
import gov.va.ocp.reference.framework.service.ServiceRequest;
import gov.va.ocp.reference.framework.service.ServiceResponse;

/**
 * Created by vgadda on 8/17/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class, JacksonAutoConfiguration.class })
public class AuditAspectTest {

	@Autowired
	AuditableService auditableService;

	@Test
	public void test() {
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest));
		AuditServiceRequest request = new AuditServiceRequest();
		request.setText("AuditServiceRequest");
		auditableService.annotatedMethod(request);
	}
}

@Component
class TestAuditableService implements AuditableService {

	@Override
	@Auditable(event = AuditEvents.REQUEST_RESPONSE, activity = "testActivity")
	public ServiceResponse annotatedMethod(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		response.addMessage(MessageSeverity.INFO, "key", "value", null);
		return response;
	}
}

interface AuditableService {
	ServiceResponse annotatedMethod(ServiceRequest request);
}

class AuditServiceRequest extends ServiceRequest {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

@Configuration
@ComponentScan({ "gov.va.ocp.reference.framework.audit",
		"gov.va.ocp.reference.starter.audit.autoconfigure" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
class Config {

}