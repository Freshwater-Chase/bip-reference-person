/**
 * Copyright (c) 2015-2016 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Code has been borrowed from https://github.com/jmnarloch/hystrix-context-spring-boot-starter
 * 
 * Updates done to address the following items:
 * 
 * 1) Call to HystrixPlugins.reset() for addressing java.lang.IllegalStateException: Another strategy was already registered.
 * 2) Register Bean RequestAttributeAwareCallableWrapper
 * 
 */
package gov.va.ocp.reference.starter.hystrix.autoconfigure;

import com.netflix.hystrix.strategy.HystrixPlugins;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs configuration of the Hystrix concurency strategy registering {@link HystrixCallableWrapper} instances that
 * has been registered in application context.
 *
 * @author Jakub Narloch
 * @author Abhijit Kulkarni
 * @see HystrixCallableWrapper
 * @see HystrixContextAwareConcurrencyStrategy
 */
@Configuration
@ConditionalOnProperty(value = "hystrix.wrappers.enabled", matchIfMissing = true)
public class HystrixContextAutoConfiguration {

    @Autowired(required = false)
    private List<HystrixCallableWrapper> wrappers = new ArrayList<>();
    
    /**
	 * A bean for registering RequestAttributeAwareCallableWrapper
	 *
	 * @return RequestAttributeAwareCallableWrapper
	 */
	@Bean
	@ConditionalOnMissingBean
	public RequestAttributeAwareCallableWrapper requestAttributeAwareCallableWrapper() {
		return new RequestAttributeAwareCallableWrapper();
	}
    
    /**
     * Configure hystrix concurency strategy.
     */
    @PostConstruct
    public void configureHystrixConcurencyStrategy() {
        if (!wrappers.isEmpty()) {
        	HystrixPlugins.reset();
            HystrixPlugins.getInstance().registerConcurrencyStrategy(
                    new HystrixContextAwareConcurrencyStrategy(wrappers)
            );
        }
    }
}
