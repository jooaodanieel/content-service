package it.unibz.archlab.digidojo.content.application.kafka;

import it.unibz.archlab.digidojo.content.application.event.PostErased;
import it.unibz.archlab.digidojo.content.application.event.PostWritten;
import it.unibz.archlab.digidojo.content.core.Broadcaster;
import it.unibz.archlab.digidojo.content.core.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer implements Broadcaster {
    @Value("${it.unibz.archlab.digidojo.content.kafka.producer.topics.messages}")
    private String messagesTopic;

    @Value("${it.unibz.archlab.digidojo.content.kafka.producer.topics.post_written}")
    private String postWrittenTopic;

    @Value("${it.unibz.archlab.digidojo.content.kafka.producer.topics.post_erased}")
    private String postErasedTopic;


    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void generate(String message) {
        String jsonMessage = messageToJson(message);
        kafkaTemplate.send(messagesTopic, jsonMessage);
    }

    private String messageToJson(String message) {
        return "{\"message\":\""+message+"\"}";
    }

    @Override
    public void emitPostWritten(Post post) {
        PostWritten postWrittenEvent = new PostWritten(post);
        kafkaTemplate.send(postWrittenTopic, postWrittenEvent.toJson());
    }

    @Override
    public void emitPostErased(Post post) {
        PostErased postErasedEvent = new PostErased(post);
        kafkaTemplate.send(postErasedTopic, postErasedEvent.toJson());
    }
}
