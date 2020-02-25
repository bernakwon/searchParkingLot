package com.berna.webclient;

import com.berna.cache.domain.ApiResult;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
public class WebClientCallTest {

    @Test
    public void api_call_성공(){
        String url = "http://openapi.seoul.go.kr:8088/496270515468796534334167537975/json/GetParkInfo/1/1";

         WebTestClient.bindToServer().baseUrl(url).build().get().uri(url).accept(MediaType.APPLICATION_JSON_UTF8)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(ApiResult.class);
    }

}
