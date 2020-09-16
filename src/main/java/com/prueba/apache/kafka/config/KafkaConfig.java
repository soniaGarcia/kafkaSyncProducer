package com.prueba.apache.kafka.config;

import com.prueba.apache.kafka.mensajeDTO.ResultMsj;
import com.prueba.apache.kafka.mensajeDTO.VehiculoMsj;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

/**
 *
 * @author sonia.cabrera
 */
@Configuration
public class KafkaConfig {

    @Bean
    public ReplyingKafkaTemplate<String, VehiculoMsj, ResultMsj> replyingKafkaTemplate(ProducerFactory<String, VehiculoMsj> pf,
            ConcurrentKafkaListenerContainerFactory<String, ResultMsj> factory) {
        ConcurrentMessageListenerContainer<String, ResultMsj> replyContainer = factory.createContainer("result");
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        replyContainer.getContainerProperties().setGroupId("vehiculo-result-group");
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

    @Bean
    public KafkaTemplate<String, ResultMsj> replyTemplate(ProducerFactory<String, ResultMsj> pf,
            ConcurrentKafkaListenerContainerFactory<String, ResultMsj> factory) {
        KafkaTemplate<String, ResultMsj> kafkaTemplate = new KafkaTemplate<>(pf);
        factory.getContainerProperties().setMissingTopicsFatal(false);
        factory.setReplyTemplate(kafkaTemplate);
        return kafkaTemplate;
    }
}
