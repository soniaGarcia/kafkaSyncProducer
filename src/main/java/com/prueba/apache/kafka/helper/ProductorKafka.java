package com.prueba.apache.kafka.helper;

import com.prueba.apache.kafka.mensajeDTO.ResultMsj;
import com.prueba.apache.kafka.mensajeDTO.VehiculoMsj;
import java.util.concurrent.ExecutionException;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Component;

@Component
public class ProductorKafka {

    @Autowired
    private ReplyingKafkaTemplate<String, VehiculoMsj, ResultMsj> replyingKafkaTemplate;

    private final String kafkaTopic = "vehiculo";

    /**
     *
     * @param vehiculo
     * @throws java.util.concurrent.ExecutionException
     * @throws java.lang.InterruptedException
     */
    @SneakyThrows
    public void sendCustomMessage(VehiculoMsj vehiculo) throws ExecutionException, InterruptedException {
        ProducerRecord<String, VehiculoMsj> record = new ProducerRecord<>(kafkaTopic, null, vehiculo.getId().getCounter() + "", vehiculo);
        RequestReplyFuture<String, VehiculoMsj, ResultMsj> future = replyingKafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, ResultMsj> response = future.get();
        ResultMsj val = response.value();
        System.out.println("codigo: " + val.getCodigo() + " descripcion:" + val.getDescripcion());

    }

}
