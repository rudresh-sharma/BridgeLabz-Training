package com.fundoonotesapp.messaging.producer;

import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteCreatedEvent;
import com.fundoonotesapp.messaging.event.NoteDeletedEvent;
import com.fundoonotesapp.messaging.event.NoteUpdatedEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteEventProducer {

    private final RabbitTemplate rabbitTemplate;


    public void publishNoteCreated(
            NoteCreatedEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTE_EXCHANGE,
                RabbitMQConfig.NOTE_CREATED_ROUTING_KEY,
                event
        );

        System.out.println(
                "NOTE CREATED EVENT SENT TO RABBITMQ: "
                        + event
        );
    }


    public void publishNoteUpdated(
            NoteUpdatedEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTE_EXCHANGE,
                RabbitMQConfig.NOTE_UPDATED_ROUTING_KEY,
                event
        );

        System.out.println(
                "NOTE UPDATED EVENT SENT TO RABBITMQ: "
                        + event
        );
    }


    public void publishNoteDeleted(
            NoteDeletedEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTE_EXCHANGE,
                RabbitMQConfig.NOTE_DELETED_ROUTING_KEY,
                event
        );

        System.out.println(
                "NOTE DELETED EVENT SENT TO RABBITMQ: "
                        + event
        );
    }
}