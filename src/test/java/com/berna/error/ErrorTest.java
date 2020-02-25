package com.berna.error;


import com.berna.cache.service.CacheService;
import com.berna.global.error.exception.APIErrorException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(PowerMockRunner.class)
public class ErrorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private CacheService cacheService;

    @Before
    public void setup() {

         cacheService = new CacheService(WebClient.builder());
    }


    @Test(expected= APIErrorException.class)
    public void API_ERROR_발생() throws Exception {
        Whitebox.setInternalState(cacheService,"baseUrl","http://openapi.seoul.go.kr:8088");
        Whitebox.setInternalState(cacheService,"key","/496270515468796534334167537975");
        Whitebox.setInternalState(cacheService,"type","/json");
        Whitebox.setInternalState(cacheService,"serviceName","/GetParkInfo");
        int wrongStartIndex =16; //endIndex보다 큰 test 변수
        int endIndex=1;
        Whitebox.invokeMethod(cacheService, "apiCallByWebClient", wrongStartIndex,endIndex);

    }
}
