package com.foodordering.service;

import com.foodordering.model.User; // Correctly importing your custom User entity

public interface UserService {

    /**
     * Finds a user by decoding the given JWT token.
     * 
     * @param jwt the JWT token
     * @return the User entity associated with the token
     * @throws Exception if the user cannot be found
     */
    public User findUserByJwtToken(String jwt) throws Exception;

    /**
     * Finds a user by their email address.
     * 
     * @param email the email address of the user
     * @return the User entity associated with the email
     * @throws Exception if the user cannot be found
     */
    public User findUserByEmail(String email) throws Exception;
}
