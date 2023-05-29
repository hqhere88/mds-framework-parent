package mds.framework.service.impl;

import mds.framework.entity.Message;
import mds.framework.service.IMessageService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Stream;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {
    @Override
    public Stream<Message> findAllByTopicSince(String topic, Instant since) {
        return null;
    }

    @Override
    public void save(Message message) {
    }
}
