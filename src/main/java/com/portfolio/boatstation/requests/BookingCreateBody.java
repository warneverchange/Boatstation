package com.portfolio.boatstation.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateBody {
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Long watercraftLogId;
}
