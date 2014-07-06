package com.trajan.seed.webapp.rest.security;

import com.trajan.seed.webapp.rest.BasicController;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-06
 */
public class AuthControllerTest extends BasicController {

    @Test
    public void shouldLoginWithGoodCredentials() throws Exception {
        ResponseEntity<String> response = loginSuccess();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldFailLoginWithBadCredential() throws Exception {
        ResponseEntity<String> response = loginFail();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void shouldFailLogoutWithoutSession() throws Exception {
        ResponseEntity<String> response = logoutFail();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void shouldLogoutWithSession() throws Exception {
        ResponseEntity<String> logoutResponse = logoutSuccess();
        assertEquals(HttpStatus.OK, logoutResponse.getStatusCode());
    }
}
