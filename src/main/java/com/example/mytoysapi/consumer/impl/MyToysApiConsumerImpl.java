package com.example.mytoysapi.consumer.impl;

import com.example.mytoysapi.common.exception.UnhealthyUpstreamServiceException;
import com.example.mytoysapi.consumer.MyToysApiConsumer;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.core.impl.ProcFilterByNode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.example.mytoysapi.common.constants.Messages.UPSTREAM_SERVICE_UNHEALTHY_MSG;
import static com.example.mytoysapi.common.constants.Messages.UPSTREAM_SERVICE_UNHEALTHY_CODE;

/**
 * Implements {@link MyToysApiConsumer}.
 */
@Component
public class MyToysApiConsumerImpl implements MyToysApiConsumer {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "readFallbackNavigation",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    // At how many requests is it looking at a time
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // How long does it wait to send another request to the defunct service
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
            },//New thread pool is created; can be seen as new bulkhead
            threadPoolKey = "mytoysApiPool"
    )
    public Navigation readNavigation() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Navigation> response = restTemplate.exchange(
                "https://mytoysiostestcase1.herokuapp.com/api/navigation", HttpMethod.GET, entity, Navigation.class);

        return response.getBody();
    }

    /**
     * Nothing can be done here apart from returning an error quickly.
     */
    public Navigation readFallbackNavigation() throws UnhealthyUpstreamServiceException {
        String errorMessage = env.getProperty(env.getProperty(UPSTREAM_SERVICE_UNHEALTHY_MSG));
        int errorCode = Integer.parseInt(env.getProperty(UPSTREAM_SERVICE_UNHEALTHY_CODE));
        LoggerFactory.getLogger(ProcFilterByNode.class).error(errorMessage);
        throw new UnhealthyUpstreamServiceException(errorCode, errorMessage);
    }
}
