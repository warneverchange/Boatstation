package com.portfolio.boatstation.errors;

import com.portfolio.boatstation.entities.views.security.User;

public record UserNotFoundErrorInfo(String url, User user, String message){}
