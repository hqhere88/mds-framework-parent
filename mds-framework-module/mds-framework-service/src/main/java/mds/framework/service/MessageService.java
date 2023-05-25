package mds.framework.service;

import mds.framework.entity.Message;

import java.time.Instant;
import java.util.stream.Stream;

public class MessageService {
    public Stream<Message> findAllByTopicSince(String topic, Instant since) {
        return null;
    }

    public void save(Message message) {
    }
}
