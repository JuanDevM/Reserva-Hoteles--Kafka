package com.example.reservahoteleskafka.reservahoteleskafka.models;

import com.amazonaws.services.sqs.model.MessageAttributeValue;

import java.util.Map;

public record ReservaAWSSqs(Map<String, MessageAttributeValue> atributosMensaje) implements ReservaMobile {

    @Override
    public String getId() {
        final String prefijoAWSSqs = "SQS-";
        return prefijoAWSSqs + this.atributosMensaje.get("id").getStringValue();
    }

    @Override
    public Long getIdhotel() {
        return Long.valueOf(this.atributosMensaje.get("idhotel").getStringValue());
    }

    @Override
    public Long getIdhabitacion() {
        return Long.valueOf(this.atributosMensaje.get("idhabitacion").getStringValue());
    }

    @Override
    public String getCedulareserva() {
        return this.atributosMensaje.get("cedulareserva").getStringValue();
    }

    @Override
    public String getFechainicioReserva() {
        return atributosMensaje.get("fechainicioReserva").getStringValue();
    }

    @Override
    public String getFechafinReserva() {
        return atributosMensaje.get("fechafinReserva").getStringValue();
    }


    @Override
    public String getValor() {
        return atributosMensaje.get("valor").getStringValue();
    }

    @Override
    public String getEstado() {
        return atributosMensaje.get("estado").getStringValue();
    }
}
