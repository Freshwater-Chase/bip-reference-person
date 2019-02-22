package gov.va.os.reference.framework.log;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.slf4j.ILoggerFactory;

import gov.va.os.reference.framework.exception.ReferenceRuntimeException;

public class ReferenceLoggerFactoryTest {

	@Test
	public final void testReferenceLoggerFactory() throws NoSuchMethodException, SecurityException {
		Constructor<ReferenceLoggerFactory> constructor = ReferenceLoggerFactory.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		try {
			constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertTrue(InvocationTargetException.class.equals(e.getClass()));
			assertTrue(ReferenceRuntimeException.class.equals(e.getCause().getClass()));
		}
	}

	@Test
	public final void testGetLoggerClass() {
		ReferenceLogger logger = ReferenceLoggerFactory.getLogger(this.getClass());
		assertNotNull(logger);
		assertTrue(logger.getName().equals(this.getClass().getName()));
	}

	@Test
	public final void testGetLoggerString() {
		ReferenceLogger logger = ReferenceLoggerFactory.getLogger(this.getClass().getName());
		assertNotNull(logger);
		assertTrue(logger.getName().equals(this.getClass().getName()));
	}

	@Test
	public final void testGetBoundFactory() {
		ILoggerFactory factory = ReferenceLoggerFactory.getBoundFactory();
		assertNotNull(factory);
	}
}
