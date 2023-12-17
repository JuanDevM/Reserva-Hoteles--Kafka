package com.example.reservahoteleskafka.reservahoteleskafka.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReservaMobile {
    String getId();
    Long getIdhotel();
    Long getIdhabitacion();
    String getCedulareserva();
    String getFechainicioReserva();
    String getFechafinReserva();
    String getValor();

    String getEstado();
}
