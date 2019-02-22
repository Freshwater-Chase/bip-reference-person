package gov.va.ocp.reference.framework.rest.provider;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import gov.va.ocp.reference.framework.audit.AuditEventData;
import gov.va.ocp.reference.framework.audit.AuditEvents;
import gov.va.ocp.reference.framework.rest.provider.BaseRestProviderAspect;

@RunWith(MockitoJUnitRunner.class)
public class BaseRestProviderAspectTest {

	@Test
	public void testRestController() {
		BaseRestProviderAspect.restController();
	}

	@Test
	public void testPublicServiceResponseRestMethod() {
		BaseRestProviderAspect.publicServiceResponseRestMethod();
	}

	@Mock
	private ProceedingJoinPoint proceedingJoinPoint;
	@Mock
	private MethodSignature signature;

	@Mock
	private JoinPoint.StaticPart staticPart;
	private Object[] value;

	@Before
	public void setUp() throws Exception {
		value = new Object[1];
		value[0] = "";
		Mockito.lenient().when(proceedingJoinPoint.getArgs()).thenReturn(value);
		Mockito.lenient().when(proceedingJoinPoint.getStaticPart()).thenReturn(staticPart);
		Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(signature);
		Mockito.lenient().when(signature.getMethod()).thenReturn(myMethod());
	}

	/**
	 * Test of auditableAnnotation method, of class BaseRestProviderAspect.
	 */
	@Test
	public void testAuditableAnnotation() {
		BaseRestProviderAspect.auditableAnnotation();
	}

	/**
	 * Test of auditableExecution method, of class BaseRestProviderAspect.
	 */
	@Test
	public void testAuditableExecution() {
		BaseRestProviderAspect.auditableExecution();
	}

	/**
	 * Test of auditRestController method, of class BaseRestProviderAspect.
	 */
	@Test
	public void testAuditRestController() {
		BaseRestProviderAspect.restController();
	}

	/**
	 * Test of getDefaultAuditableInstance method, of class BaseRestProviderAspect.
	 */
	@Test
	public void testGetDefaultAuditableInstance() throws Exception {
		Method method = myMethod();
		AuditEvents expResult = AuditEvents.REQUEST_RESPONSE;
		AuditEventData result = BaseRestProviderAspect.getDefaultAuditableInstance(method);
		assertEquals(expResult, result.getEvent());
		assertEquals("someMethod", result.getActivity());
		assertEquals("gov.va.ocp.reference.framework.rest.provider.BaseRestProviderAspectTest", result.getAuditClass());

	}

	/**
	 * Test of getDefaultAuditableInstance method, of class BaseRestProviderAspect.
	 */
	@Test
	public void testGetDefaultAuditableInstanceMethodNotNull() throws Exception {
		Method method = myMethod();
		AuditEvents expResult = AuditEvents.REQUEST_RESPONSE;
		AuditEventData result = BaseRestProviderAspect.getDefaultAuditableInstance(method);
		assertEquals(expResult, result.getEvent());
		assertEquals(method.getName(), result.getActivity());
		assertEquals(method.getDeclaringClass().getName(), result.getAuditClass());
	}

	/**
	 * Test of getDefaultAuditableInstance method, of class BaseRestProviderAspect.
	 */
	@Test
	public void testGetDefaultAuditableInstanceMethodNull() throws Exception {
		Method method = null;
		AuditEvents expResult = AuditEvents.REQUEST_RESPONSE;
		AuditEventData result = BaseRestProviderAspect.getDefaultAuditableInstance(method);
		assertEquals(expResult, result.getEvent());
		assertEquals("Unknown", result.getActivity());
		assertEquals("Unknown", result.getAuditClass());
	}

	public Method myMethod() throws NoSuchMethodException {
		return getClass().getDeclaredMethod("someMethod");
	}

	public void someMethod() {
		// do nothing
	}

}
