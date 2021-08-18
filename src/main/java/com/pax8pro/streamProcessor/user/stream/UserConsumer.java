package com.pax8pro.streamProcessor.user.stream;

import com.pax8pro.streamProcessor.preference.domain.UserWithPreference;
import lombok.AllArgsConstructor;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
@Component("userConsumer")
public class UserConsumer implements Consumer<KStream<UUID, UserWithPreference>> {

    @Override
    public void accept(KStream<UUID, UserWithPreference> stream) {
        stream.peek((key, value) -> System.out.printf("key: %s, value: %s\n", key, value));
    }
}
