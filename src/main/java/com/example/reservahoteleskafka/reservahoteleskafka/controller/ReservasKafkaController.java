package com.example.reservahoteleskafka.reservahoteleskafka.controller;

import com.amazonaws.services.sqs.model.Message;
import com.example.reservahoteleskafka.reservahoteleskafka.models.ReservaWebAplication;
import com.example.reservahoteleskafka.reservahoteleskafka.models.Topicos;
import com.example.reservahoteleskafka.reservahoteleskafka.services.ReservaSQSService;
import com.example.reservahoteleskafka.reservahoteleskafka.services.ReservaWebAplicationKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva-hoteles")
@RequiredArgsConstructor
public class ReservasKafkaController {

    private ReservaWebAplicationKafkaService reservaWebAplicationKafkaService;
    private ReservaSQSService reservaSQSService;

    @PostMapping("/")
    public ReservaWebAplication enviarReservaWebAplicationKafka(@RequestBody ReservaWebAplication reservaWebAplication){
        reservaWebAplicationKafkaService.send(String.valueOf(Topicos.RESERVAS_WEBAPLICATION), reservaWebAplication);
        return reservaWebAplication;
    }

    @PostMapping("/sqs")
    public String enviarReservaDesdeSQSHaciaKafka(){
        //TODO: Hacer dinamico el parametro del nombre de la cola
        List<Message> awsSqsMessages = reservaSQSService.receiveMessagesFromQueue("reservas-hoteles", 10, 10);
        return reservaWebAplicationKafkaService.sendAWSSqsListMessagesToKafka(awsSqsMessages, String.valueOf(Topicos.RESERVAS_SQS));
    }

}
