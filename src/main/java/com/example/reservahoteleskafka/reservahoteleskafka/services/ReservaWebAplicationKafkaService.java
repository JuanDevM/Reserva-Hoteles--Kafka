package com.example.reservahoteleskafka.reservahoteleskafka.services;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.example.reservahoteleskafka.reservahoteleskafka.models.ReservaAWSSqs;
import com.example.reservahoteleskafka.reservahoteleskafka.models.ReservaMobile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Slf4j
@AllArgsConstructor
@Service
public class ReservaWebAplicationKafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topico, ReservaMobile reservaMobile){
        var future = kafkaTemplate.send(topico, reservaMobile.getId(), reservaMobile.toString());

        future.whenComplete((resultadoEnvio, excepcion)->{
            if(excepcion != null){
                log.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                future.complete(resultadoEnvio);
                log.info("Reserva Web Aplication enviado al topico -> " + topico + " en Kafka " + reservaMobile.toString());
            }
        });
    }

    private List<ReservaMobile> transformReservaFromAWSSqsToReservaMobile(List<Message> messages) {
        List<ReservaMobile> reservas = new LinkedList<>();
        for(Message message: messages){
            Map<String, MessageAttributeValue> atributosMensaje = message.getMessageAttributes();
            ReservaAWSSqs reservaAWSSqs = new ReservaAWSSqs(atributosMensaje);
            reservas.add(reservaAWSSqs);
        }
        return reservas;
    }

    public String sendAWSSqsListMessagesToKafka(List<Message> messages, String topico){
        List<ReservaMobile> reservasMobile = transformReservaFromAWSSqsToReservaMobile(messages);
        for(ReservaMobile reservaMobile: reservasMobile){
            send(topico, reservaMobile);
        }
        return "Se han enviado " + reservasMobile.size() + " reservas desde AWSSqs hacia Kafka";
    }
}
