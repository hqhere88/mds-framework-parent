package mds.framework.service;

import mds.framework.entity.Message;

import java.time.Instant;
import java.util.stream.Stream;

public interface IMessageService {

    Stream<Message> findAllByTopicSince(String topic, Instant since);

    void save(Message message);
}
