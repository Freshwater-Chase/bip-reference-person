package gov.va.ocp.reference.framework.rest.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import gov.va.ocp.reference.framework.AbstractBaseLogTester;
import gov.va.ocp.reference.framework.audit.AuditEvents;
import gov.va.ocp.reference.framework.audit.Auditable;
import gov.va.ocp.reference.framework.exception.OcpRuntimeException;
import gov.va.ocp.reference.framework.log.OcpLogger;
import gov.va.ocp.reference.framework.messages.HttpStatusForMessage;
import gov.va.ocp.reference.framework.messages.Message;
import gov.va.ocp.reference.framework.messages.MessageSeverity;
import gov.va.ocp.reference.framework.service.DomainRequest;
import gov.va.ocp.reference.framework.service.DomainResponse;

@RunWith(MockitoJUnitRunner.class)
public class RestProviderHttpResponseAspectTest extends AbstractBaseLogTester {

	private final OcpLogger restProviderLog = super.getLogger(RestProviderHttpResponseAspect.class);

	private RestProviderHttpResponseAspect restProviderHttpResponseAspect;
	@Mock
	private ProceedingJoinPoint proceedingJoinPoint;

	@Mock
	private ResponseEntity<DomainResponse> responseEntity;

	@Mock
	private DomainResponse domainResponse;

	@Mock
	private MethodSignature mockSignature;

	@InjectMocks
	private final RestProviderHttpResponseAspect requestResponseAspect = new RestProviderHttpResponseAspect();

	private final TestServiceRequest mockRequestObject = new TestServiceRequest();
	private final Object[] mockArray = { mockRequestObject };

	private final List<Message> detailedMsg = new ArrayList<Message>();

	@Before
	public void setUp() throws Exception {
		super.getAppender().clear();

		final MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		final MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

		httpServletRequest.setContentType("multipart/form-data");
		final MockPart userData = new MockPart("userData", "userData", "{\"name\":\"test aida\"}".getBytes());
		httpServletRequest.addPart(userData);

		httpServletRequest.addHeader("TestHeader", "TestValue");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest, httpServletResponse));

		super.getAppender().clear();
		restProviderLog.setLevel(Level.DEBUG);
		try {
			Mockito.lenient().when(proceedingJoinPoint.getArgs()).thenReturn(mockArray);
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());

			final Message msg = new Message(MessageSeverity.FATAL, "FatalKey", "Fatal Message", null);
			detailedMsg.add(msg);
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenReturn(responseEntity);
			Mockito.lenient().when(responseEntity.getBody()).thenReturn(domainResponse);
			Mockito.lenient().when(domainResponse.getMessages()).thenReturn(detailedMsg);
		} catch (final Throwable e) {

		}

	}

	@Override
	@After
	public void tearDown() {
	}

	@Test
	public void testMultipartFormData() {

		final MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		final MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

		httpServletRequest.setContentType("multipart/form-data");
		final MockPart userData = new MockPart("userData", "userData", "{\"name\":\"test aida\"}".getBytes());
		httpServletRequest.addPart(userData);
		httpServletRequest.addHeader("TestHeader", "TestValue");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest, httpServletResponse));

		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
		Object returnObject = null;
		try {
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenReturn(domainResponse);
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());

			returnObject = restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint);
		} catch (final Throwable throwable) {

		}
		assertNotNull(returnObject);
	}

	@Test
	public void testMultipartmixed() {

		final MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		final MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

		httpServletRequest.setContentType("multipart/mixed");
		final MockPart userData = new MockPart("userData", "userData", "{\"name\":\"test aida\"}".getBytes());
		httpServletRequest.addPart(userData);
		httpServletRequest.addHeader("TestHeader", "TestValue");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest, httpServletResponse));

		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
		Object returnObject = null;
		try {
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenReturn(domainResponse);
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());

			returnObject = restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint);
		} catch (final Throwable throwable) {

		}
		assertNotNull(returnObject);
	}

	@Test
	public void testServiceResponseReturnType() {
		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
		Object returnObject = null;
		try {
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenReturn(domainResponse);
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());

			returnObject = restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint);
		} catch (final Throwable throwable) {

		}
		assertNotNull(returnObject);
	}

	@Test
	public void testServiceResponseReturnTypes() {
		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
		Object returnObject = null;
		try {
			final DomainResponse serviceResp = new DomainResponse();
			serviceResp.addMessage(MessageSeverity.FATAL, "Test KEY", "Test Error", HttpStatusForMessage.INTERNAL_SERVER_ERROR);
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenReturn(serviceResp);
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());

			returnObject = restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint);
		} catch (final Throwable throwable) {

		}
		assertNotNull(returnObject);
	}

	@Test
	public void testConstructorWithParam() {
		final MessagesToHttpStatusRulesEngine ruleEngine = new MessagesToHttpStatusRulesEngine();
		ruleEngine.addRule(new MessageSeverityMatchRule(MessageSeverity.FATAL, HttpStatus.INTERNAL_SERVER_ERROR));
		ruleEngine.addRule(new MessageSeverityMatchRule(MessageSeverity.ERROR, HttpStatus.BAD_REQUEST));
		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect(ruleEngine);
		assertNotNull(restProviderHttpResponseAspect);

	}

	@Test
	public void testAroundAdvice() {
		try {
			restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
			assertNull(restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint));
		} catch (final Throwable e) {

		}

	}

	@Test
	public void testAroundAdviceCatchReferenceExceptionLogging() {
		super.getAppender().clear();

		restProviderLog.setLevel(Level.ERROR);
		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
		Object returnObject = null;
		try {
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenThrow(new OcpRuntimeException());
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());

			returnObject = restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint);
		} catch (final Throwable throwable) {

		}
		assertTrue(((DomainResponse) returnObject).getMessages().size() > 0);
	}

	@Test
	public void testAroundAdviceCatchExceptionLogging() {
		super.getAppender().clear();
		restProviderLog.setLevel(Level.ERROR);

		restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
		Object returnObject = null;
		try {
			Mockito.lenient().when(proceedingJoinPoint.proceed())
					.thenThrow(new Throwable("Unit Test Throwable converted to ReferenceRuntimException"));
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());
			returnObject = restProviderHttpResponseAspect.aroundAdvice(proceedingJoinPoint);
		} catch (final Throwable throwable) {

		}

		assertTrue(((DomainResponse) returnObject).getMessages().size() > 0);
	}

	@Test
	public void testAnnotatedMethodRequestResponse() {
		Object obj;
		try {
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenReturn(domainResponse);
			Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(mockSignature);
			Mockito.lenient().when(mockSignature.getMethod()).thenReturn(myAnnotatedMethod());
			Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(new TestClass());

			restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
			obj = restProviderHttpResponseAspect.logAnnotatedMethodRequestResponse(proceedingJoinPoint);
			assertNotNull(obj);
		} catch (final Throwable throwable) {
			assertTrue(throwable instanceof RuntimeException);
		}
	}

	@Test
	public void testAnnotatedMethodRequestResponseRunTimeException() {

		try {
			final Object[] array = { null, new Object() };
			Mockito.lenient().when(proceedingJoinPoint.getArgs()).thenReturn(array);
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenThrow(new RuntimeException("Unit Test Exception"));
			restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
			restProviderHttpResponseAspect.logAnnotatedMethodRequestResponse(proceedingJoinPoint);
		} catch (final Throwable throwable) {
			assertTrue(throwable instanceof RuntimeException);
		}

	}

	@Test
	public void testAnnotatedMethodRequestResponseRunTimeExceptionArrayZero() {

		try {
			final Object[] array = new Object[0];
			Mockito.lenient().when(proceedingJoinPoint.getArgs()).thenReturn(array);
			Mockito.lenient().when(proceedingJoinPoint.proceed()).thenThrow(new RuntimeException("Unit Test Exception"));
			restProviderHttpResponseAspect = new RestProviderHttpResponseAspect();
			restProviderHttpResponseAspect.logAnnotatedMethodRequestResponse(proceedingJoinPoint);
		} catch (final Throwable throwable) {
			assertTrue(throwable instanceof RuntimeException);
		}

	}

	@Test
	public void testGetReturnResponse() {
		final RestProviderHttpResponseAspect aspect = new RestProviderHttpResponseAspect();
		Method method = null;
		Object retval = null;
		try {
			method = aspect.getClass().getDeclaredMethod("getReturnResponse", boolean.class, Object.class);
			method.setAccessible(true);
			retval = method.invoke(aspect, Boolean.TRUE, new ResponseEntity<DomainResponse>(HttpStatus.valueOf(200)));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			fail("Should not have exception here");
		}
		assertNull(retval);

		try {
			retval = method.invoke(aspect, Boolean.FALSE, "hello");
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			fail("Should not have exception here");
		}
		assertNotNull(retval);
		assertTrue("hello".equals(retval));
	}

	public Method myMethod() throws NoSuchMethodException {
		return getClass().getDeclaredMethod("someMethod");
	}

	public void someMethod() {
		// do nothing
	}

	public Method myAnnotatedMethod() throws NoSuchMethodException {
		return getClass().getDeclaredMethod("annotatedMethod");
	}

	@Auditable(event = AuditEvents.REQUEST_RESPONSE, activity = "testActivity")
	public void annotatedMethod() {
		// do nothing
	}

	class TestClass {

	}
}

class TestServiceRequest extends DomainRequest {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8736731329416969081L;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}
}
