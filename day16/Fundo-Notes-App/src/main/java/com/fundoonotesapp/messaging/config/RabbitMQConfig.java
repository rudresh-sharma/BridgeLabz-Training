package com.fundoonotesapp.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // ================= QUEUES =================

    public static final String NOTE_SEARCH_QUEUE =
            "note.search.queue";

    public static final String NOTE_CREATED_AUDIT_QUEUE =
            "note.created.audit.queue";

    public static final String NOTE_UPDATED_AUDIT_QUEUE =
            "note.updated.audit.queue";
    
    public static final String NOTE_DELETED_SEARCH_QUEUE =
            "note.deleted.search.queue";
    public static final String NOTE_DELETED_AUDIT_QUEUE =
            "note.deleted.audit.queue";


    // ================= EXCHANGE =================

    public static final String NOTE_EXCHANGE =
            "note.exchange";


    // ================= ROUTING KEYS =================

    public static final String NOTE_CREATED_ROUTING_KEY =
            "note.created";

    public static final String NOTE_UPDATED_ROUTING_KEY =
            "note.updated";

    public static final String NOTE_DELETED_ROUTING_KEY =
            "note.deleted";


    // ================= MESSAGE CONVERTER =================

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }


    // ================= EXCHANGE =================

    @Bean
    public TopicExchange noteExchange() {
        return new TopicExchange(NOTE_EXCHANGE);
    }


    // ================= SEARCH QUEUE =================

    @Bean
    public Queue noteSearchQueue() {
        return new Queue(NOTE_SEARCH_QUEUE, true);
    }


    // ================= AUDIT QUEUES =================

    @Bean
    public Queue noteCreatedAuditQueue() {
        return new Queue(NOTE_CREATED_AUDIT_QUEUE, true);
    }

    @Bean
    public Queue noteUpdatedAuditQueue() {
        return new Queue(NOTE_UPDATED_AUDIT_QUEUE, true);
    }

    @Bean
    public Queue noteDeletedAuditQueue() {
        return new Queue(NOTE_DELETED_AUDIT_QUEUE, true);
    }

    @Bean
    public Queue noteDeletedSearchQueue() {
        return new Queue(NOTE_DELETED_SEARCH_QUEUE, true);
    }

    // =====================================================
    // SEARCH QUEUE BINDINGS
    // Search receives all note events
    // =====================================================

    @Bean
    public Binding noteCreatedSearchBinding(
            @Qualifier("noteSearchQueue") Queue noteSearchQueue,
            TopicExchange noteExchange
    ) {
        return BindingBuilder.bind(noteSearchQueue)
                .to(noteExchange)
                .with(NOTE_CREATED_ROUTING_KEY);
    }


    @Bean
    public Binding noteUpdatedSearchBinding(
            @Qualifier("noteSearchQueue") Queue noteSearchQueue,
            TopicExchange noteExchange
    ) {
        return BindingBuilder.bind(noteSearchQueue)
                .to(noteExchange)
                .with(NOTE_UPDATED_ROUTING_KEY);
    }


    @Bean
    public Binding noteDeletedSearchBinding(
            @Qualifier("noteSearchQueue") Queue noteSearchQueue,
            TopicExchange noteExchange
    ) {
        return BindingBuilder.bind(noteSearchQueue)
                .to(noteExchange)
                .with(NOTE_DELETED_ROUTING_KEY);
    }


    // =====================================================
    // AUDIT QUEUE BINDINGS
    // Each queue receives only its own event type
    // =====================================================

    @Bean
    public Binding noteCreatedAuditBinding(
            @Qualifier("noteCreatedAuditQueue") Queue noteCreatedAuditQueue,
            TopicExchange noteExchange
    ) {
        return BindingBuilder.bind(noteCreatedAuditQueue)
                .to(noteExchange)
                .with(NOTE_CREATED_ROUTING_KEY);
    }


    @Bean
    public Binding noteUpdatedAuditBinding(
            @Qualifier("noteUpdatedAuditQueue") Queue noteUpdatedAuditQueue,
            TopicExchange noteExchange
    ) {
        return BindingBuilder.bind(noteUpdatedAuditQueue)
                .to(noteExchange)
                .with(NOTE_UPDATED_ROUTING_KEY);
    }


    @Bean
    public Binding noteDeletedAuditBinding(
            @Qualifier("noteDeletedAuditQueue") Queue noteDeletedAuditQueue,
            TopicExchange noteExchange
    ) {
        return BindingBuilder.bind(noteDeletedAuditQueue)
                .to(noteExchange)
                .with(NOTE_DELETED_ROUTING_KEY);
    }
}