package gov.va.os.reference.starter.audit.autoconfigure;

import org.junit.After;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import gov.va.os.reference.framework.audit.RequestResponseLogSerializer;
import gov.va.os.reference.starter.audit.autoconfigure.ReferenceAuditAutoConfiguration;

import static org.junit.Assert.*;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

/**
 * Created by rthota on 8/24/17.
 */
public class ReferenceAuditAutoConfigurationTest {

    private AnnotationConfigWebApplicationContext context;

    @After
    public void close() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void testWebConfiguration() throws Exception {
        context = new AnnotationConfigWebApplicationContext();
        context.register(JacksonAutoConfiguration.class, ReferenceAuditAutoConfiguration.class);
        context.refresh();
        assertNotNull(context);
        assertNotNull(this.context.getBean(RequestResponseLogSerializer.class));
    }
}
