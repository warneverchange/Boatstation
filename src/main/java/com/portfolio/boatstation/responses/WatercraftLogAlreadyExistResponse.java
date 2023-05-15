package com.portfolio.boatstation.responses;

import com.portfolio.boatstation.entities.WatercraftLog;

public record WatercraftLogAlreadyExistResponse(WatercraftLog log, String reason){}
