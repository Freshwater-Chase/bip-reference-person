package gov.va.os.reference.framework.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Auditable annotation that can be applied to methods or classes.
 * Created by vgadda on 8/17/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Auditable {
    AuditEvents event();
    String activity();
    String auditClass() default "";
}
