package com.portfolio.boatstation.responses;

import com.portfolio.boatstation.entities.security.User;

public record UserNotFoundResponse(String url, User user, String message){}
