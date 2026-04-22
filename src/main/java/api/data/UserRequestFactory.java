package api.data;

import api.model.request.CreateUserRequest;
import api.model.request.UpdateUserRequest;

public class UserRequestFactory {

    // ===== BASE HAPPY PATH =====

    public static CreateUserRequest createValidUser() {
        return CreateUserRequest.builder()
                .name("Jan")
                .job("tester")
                .build();
    }

    public static UpdateUserRequest updateValidUser() {
        return UpdateUserRequest.builder()
                .name("Marek")
                .job("cleaning")
                .build();
    }

    // ===== MINIMAL EDGE HELPERS =====

    public static CreateUserRequest emptyCreateUser() {
        return CreateUserRequest.builder().build();
    }

    public static UpdateUserRequest emptyUpdateUser() {
        return UpdateUserRequest.builder().build();
    }
}