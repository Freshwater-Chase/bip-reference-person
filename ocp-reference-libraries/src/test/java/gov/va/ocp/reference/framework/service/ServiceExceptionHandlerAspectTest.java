package gov.va.ocp.reference.framework.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.event.Level;

import gov.va.ocp.reference.framework.exception.ExceptionToExceptionTranslationHandler;
import gov.va.ocp.reference.framework.log.ReferenceLogger;
import gov.va.ocp.reference.framework.log.ReferenceLoggerFactory;
import gov.va.ocp.reference.framework.service.ServiceExceptionHandlerAspect;

@RunWith(MockitoJUnitRunner.class)
public class ServiceExceptionHandlerAspectTest {

	private ReferenceLogger logger = ReferenceLoggerFactory.getLogger(ServiceExceptionHandlerAspect.class);

	@Mock
	private JoinPoint joinPoint;

	@Mock
	private MethodSignature signature;

	@Mock
	private JoinPoint.StaticPart staticPart;

	final Set<Class<? extends Throwable>> exclusionSet = new HashSet<>();

	@Mock
	private Throwable throwable;

	@Mock
	private RuntimeException appRuntimeException;

	private Object[] value;

	@Before
	public void setUp() throws Exception {
		logger.setLevel(Level.DEBUG);
		value = new Object[2];
		value[0] = "arg 1";
		value[1] = "arg 2";
		when(joinPoint.getArgs()).thenReturn(value);
		when(joinPoint.getStaticPart()).thenReturn(staticPart);
		when(staticPart.getSignature()).thenReturn(signature);
		when(signature.getMethod()).thenReturn(myMethod());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAfterThrowing() throws Throwable {
		try {
			ServiceExceptionHandlerAspect mockServiceExceptionHandlerAspect = new ServiceExceptionHandlerAspect();
			mockServiceExceptionHandlerAspect.afterThrowing(joinPoint, throwable);

			Assert.fail("expected exception");
		} catch (Throwable throwable) {
			Assert.assertTrue(throwable instanceof RuntimeException);
			assertNotNull(throwable.getCause());
		}
	}

	@Test
	public void testAfterThrowingNoDebug() throws Throwable {
		logger.setLevel(Level.ERROR);
		ExceptionToExceptionTranslationHandler exceptionToExceptionTranslationHandler = new ExceptionToExceptionTranslationHandler();
		try {
			ServiceExceptionHandlerAspect mockServiceExceptionHandlerAspect =
					new ServiceExceptionHandlerAspect(exceptionToExceptionTranslationHandler);
			mockServiceExceptionHandlerAspect.afterThrowing(joinPoint, throwable);

			Assert.fail("expected exception");
		} catch (Throwable throwable) {
			Assert.assertTrue(throwable instanceof RuntimeException);
			assertNotNull(throwable.getCause());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() throws IllegalArgumentException {
		new ServiceExceptionHandlerAspect(null);
	}

	@Test
	public void testConstructorNotNull() throws IllegalArgumentException {
		ExceptionToExceptionTranslationHandler exceptionToExceptionTranslationHandler = new ExceptionToExceptionTranslationHandler();
		ServiceExceptionHandlerAspect serviceExceptionHandlerAspect =
				new ServiceExceptionHandlerAspect(exceptionToExceptionTranslationHandler);
		assertNotNull(serviceExceptionHandlerAspect);
	}

	public Method myMethod() throws NoSuchMethodException {
		return getClass().getDeclaredMethod("someMethod");
	}

	public void someMethod() {
		// do nothing
	}
}
