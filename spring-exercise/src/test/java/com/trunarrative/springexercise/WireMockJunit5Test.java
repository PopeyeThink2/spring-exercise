package com.trunarrative.springexercise;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @className: WireMockJunit5Test
 * @author: wenjie.xia
 * @description: integrations WireMock tests
 * @date: 12/06/2022 13:57
 * @version: 1.0
 */
public class WireMockJunit5Test {
    WireMockServer wireMockServer;

    private static final String URL = "http://localhost:8080/TruProxyAPI/v1/Search";

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        setupStub();
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/TruProxyAPI/v1/Search?companyNumber=06500244"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("searchByCompanyNumber.json")));
        wireMockServer.stubFor(get(urlEqualTo("/TruProxyAPI/v1/Search?companyName=BBC"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("searchByCompanyName.json")));
        wireMockServer.stubFor(get(urlEqualTo("/TruProxyAPI/v1/Search?companyName=BBC&onlyActive=true"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("searchByCompanyNameOnlyActive.json")));
        wireMockServer.stubFor(get(urlEqualTo("/TruProxyAPI/v1/Search?companyNumber=100"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("{\"items\":[],\"total_items\":0}")));
    }

    @Test
    public void testSearchByNumber() {
        given().
                when().
                get(URL + "?companyNumber=06500244").
                then().
                assertThat().statusCode(200);
        Response response =  given().when().get(URL + "?companyNumber=06500244");
        Integer total_items = response.jsonPath().get("total_items");
        assertEquals(1, total_items);
    }

    @Test
    public void testSearchByName() {
        given().
                when().
                get(URL + "?companyName=BBC").
                then().
                assertThat().statusCode(200);
        Response response =  given().when().get(URL + "?companyName=BBC");
        Integer total_items = response.jsonPath().get("total_items");
        assertEquals(20, total_items);
    }

    @Test
    public void testSearchByNameOnlyActive() {
        given().
                when().
                get(URL + "?companyName=BBC&onlyActive=true").
                then().
                assertThat().statusCode(200);
        Response response =  given().when().get(URL + "?companyName=BBC&onlyActive=true");
        Integer total_items = response.jsonPath().get("total_items");
        assertEquals(18, total_items);
    }

    @Test
    public void testSearchNotFound() {
        given().
                when().
                get(URL + "?companyNumber=100").
                then().
                assertThat().statusCode(404);
    }


}
