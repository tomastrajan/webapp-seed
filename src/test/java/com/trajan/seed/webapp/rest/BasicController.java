package com.trajan.seed.webapp.rest;

import com.trajan.seed.webapp.Application;
import com.trajan.seed.webapp.rest.security.dto.LoginRequest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-06
 */
@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class BasicController {

    protected static final String API_SECURE_ROOT = "api/secure/";

    private static final String LOGIN_URL = "auth/login/";
    private static final String LOGOUT_URL = "auth/logout/";

    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";
    private static final String PASSWORD_INVALID = "badPassword";

    protected static final String URL = "http://localhost:8080/";
    protected static final RestTemplate template = new TestRestTemplate();

    private static HttpHeaders authenticatedHeaders;


    protected <T> ResponseEntity<T> unauthenticatedRequest(String url, HttpMethod method, Object body, Class<T> responseType) {
        return template.exchange(url, method, new HttpEntity(body), responseType);
    }

    protected <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        return template.exchange(url, HttpMethod.GET, authenticatedRequest(null), responseType);
    }

    protected <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        return template.exchange(url, HttpMethod.POST, authenticatedRequest(body), responseType);
    }

    protected <T> ResponseEntity<T> put(String url, Object body, Class<T> responseType) {
        return template.exchange(url, HttpMethod.PUT, authenticatedRequest(body), responseType);
    }

    protected <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        return template.exchange(url, HttpMethod.DELETE, authenticatedRequest(null), responseType);
    }


    protected ResponseEntity<String> login(String username, String password) {
        return unauthenticatedRequest(URL + LOGIN_URL, HttpMethod.POST, createLoginRequest(username, password), String.class);
    }

    protected ResponseEntity<String> loginSuccess() {
        return login(USERNAME, PASSWORD);
    }

    protected ResponseEntity<String> loginFail() {
        return login(USERNAME, PASSWORD_INVALID);
    }

    protected ResponseEntity<String> logoutSuccess() {
        ResponseEntity<String> response =  post(URL + LOGOUT_URL, null, String.class);
        authenticatedHeaders = null;
        return response;
    }

    protected ResponseEntity<String> logoutFail() {
        authenticatedHeaders = null;
        return unauthenticatedRequest(URL + LOGOUT_URL, HttpMethod.POST, null, String.class);
    }

    private HttpEntity authenticatedRequest(Object payload) {
        if (authenticatedHeaders == null) {
            authenticatedHeaders = new HttpHeaders();
            authenticatedHeaders.add("Cookie", loginSuccess().getHeaders().get("Set-Cookie").get(1));
            authenticatedHeaders.add("Content-Type", "application/json");
            return new HttpEntity(payload, authenticatedHeaders);
        } else {
            return new HttpEntity(payload, authenticatedHeaders);
        }
    }

    private LoginRequest createLoginRequest(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginRequest;
    }

}
