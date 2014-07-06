package com.trajan.seed.webapp.rest.security;

import com.trajan.seed.webapp.domain.security.User;
import com.trajan.seed.webapp.rest.BasicController;
import com.trajan.seed.webapp.rest.exception.OperationOnNonexistentResourceException;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-06
 */
public class UserControllerTest extends BasicController {

    private static final String RESOURCE_URL = URL + API_SECURE_ROOT + "user/";
    private static final String PROPERTY_EXCEPTION = "exception";
    private static final String USERNAME = "test/";
    private static final String USERNAME1 = "user1";
    private static final String PASSWORD1 = "pass1";
    private static final String USERNAME2 = "user2";
    private static final String PASSWORD2 = "pass2";
    private static final String USERNAME3 = "user3";
    private static final String PASSWORD3 = "pass3";
    private static final String USERNAME_NONEXISTENT = "idontexist";
    private static final String NAME = "Decius";
    private static final String SURNAME = "Trajan";

    @Test
    public void shouldFailWhenAccessingMethodsWithoutAuthentication() {

        ResponseEntity<String> response;

        response = unauthenticatedRequest(RESOURCE_URL, HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        response = unauthenticatedRequest(RESOURCE_URL, HttpMethod.POST, null, String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        response = unauthenticatedRequest(RESOURCE_URL + USERNAME, HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        response = unauthenticatedRequest(RESOURCE_URL + USERNAME, HttpMethod.POST, null, String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void shouldCreateNewUser() {
        ResponseEntity<User> user = createAndGetUser(USERNAME1, PASSWORD1);
        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals(USERNAME1, user.getBody().getUsername());
    }

    @Test
    public void shouldEditUserData() {
        ResponseEntity<User> response = createAndGetUser(USERNAME2, PASSWORD2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody().getName());
        assertEquals(null, response.getBody().getSurname());

        User user = response.getBody();
        user.setName(NAME);
        user.setSurname(SURNAME);
        put(RESOURCE_URL + USERNAME2, user, String.class);

        ResponseEntity<User> editedUser = get(RESOURCE_URL + USERNAME2, User.class);
        assertEquals(HttpStatus.OK, editedUser.getStatusCode());
        assertEquals(NAME, editedUser.getBody().getName());
        assertEquals(SURNAME, editedUser.getBody().getSurname());
    }

    @Test
    public void shouldDeleteUser() {
        ResponseEntity<User> response = createAndGetUser(USERNAME3, PASSWORD3);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USERNAME3, response.getBody().getUsername());

        delete(RESOURCE_URL + USERNAME3, String.class);

        ResponseEntity<User> deletedUser = get(RESOURCE_URL + USERNAME3, User.class);
        assertEquals(HttpStatus.OK, deletedUser.getStatusCode());
        assertEquals(null, deletedUser.getBody());
    }

    @Test
    public void shouldFailWhenEditingNonexistentUser() {
        ResponseEntity<LinkedHashMap> response = put(RESOURCE_URL + USERNAME_NONEXISTENT, new User(),
                LinkedHashMap.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(OperationOnNonexistentResourceException.class.getCanonicalName(),
                response.getBody().get(PROPERTY_EXCEPTION));
    }

    private ResponseEntity<User> createAndGetUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ResponseEntity<String> response = post(RESOURCE_URL, user, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        return get(RESOURCE_URL + username, User.class);
    }

}
