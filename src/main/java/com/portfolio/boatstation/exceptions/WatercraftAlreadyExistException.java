package com.portfolio.boatstation.exceptions;

import com.portfolio.boatstation.entities.WatercraftLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = HttpStatus.CONFLICT)
public class WatercraftAlreadyExistException extends RuntimeException{
    private WatercraftLog watercraftLog;

    public WatercraftAlreadyExistException(WatercraftLog watercraftLog, String message) {
        super(message);
        this.watercraftLog = watercraftLog;
    }
}
