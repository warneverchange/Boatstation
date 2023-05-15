package com.portfolio.boatstation.responses;


import com.portfolio.boatstation.requests.BookingCreateBody;

public record BookingResponse(String reason, BookingCreateBody bookingCreateBody){}