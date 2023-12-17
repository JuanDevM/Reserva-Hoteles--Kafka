package com.example.reservahoteleskafka.reservahoteleskafka.models;

import java.time.LocalDate;

public record ReservaWebAplication(Long id, Long idhotel, Long idhabitacion, String cedulareserva, LocalDate fechainicioReserva, LocalDate fechafinReserva, String valor, String estado) implements ReservaMobile {

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public Long getIdhotel() {
        return Long.valueOf(this.idhotel);
    }

    @Override
    public Long getIdhabitacion() {
        return Long.valueOf(this.idhabitacion);
    }

    @Override
    public String getCedulareserva() {
        return this.cedulareserva;
    }

    @Override
    public String getFechainicioReserva() {
        return this.fechainicioReserva.toString();
    }

    @Override
    public String getFechafinReserva() {
        return this.fechafinReserva.toString();
    }

    @Override
    public String getValor() {
        return this.valor;
    }

    @Override
    public String getEstado() {
        return this.estado;
    }
}
