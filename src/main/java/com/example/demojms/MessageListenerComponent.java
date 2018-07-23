package com.example.demojms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerComponent implements ApplicationRunner {

    @Autowired private JmsTemplate jmsTemplate;
    @Autowired private JmsTemplate jmsTemplateTopic;


    @JmsListener(destination = "queue.sample")
    public void onReceiverQueue(String str) {
		System.out.println("LENDO FILA");
		System.out.println("\n");
        System.out.println( str );
		System.out.println("##########################################");
    }

    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(String str) {
		System.out.println("LENDO TOPICO");
		System.out.println("\n");
        System.out.println( str );
		System.out.println("##########################################");
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
    	while(true) {
    		System.out.println("\n");
    		System.out.println("##########################################");
    		System.out.println("ADICIONANDO MENSAGEM NA FILA E NO TOPICO");
    		System.out.println("##########################################");
	        jmsTemplate.convertAndSend("queue.sample", "{user: 'ciandt', usando: 'fila'}");
	        jmsTemplateTopic.convertAndSend("topic.sample", "{user: 'ciandt', usando: 'tópico'}");
            Thread.sleep(180000L);
    	}
    }
}
