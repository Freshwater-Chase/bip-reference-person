package gov.va.os.reference.starter.modelvalidator.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.va.os.reference.framework.validation.ModelValidator;


/**
 * Created by rthota on 8/24/17.
 */

@Configuration
public class ReferenceModelValidatorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ModelValidator modelValidator(){
        return new ModelValidator();
    }

}


