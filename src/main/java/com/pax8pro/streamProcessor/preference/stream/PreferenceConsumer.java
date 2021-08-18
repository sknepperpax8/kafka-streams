package com.pax8pro.streamProcessor.preference.stream;

import com.pax8pro.streamProcessor.preference.domain.Preference;
import lombok.AllArgsConstructor;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
@Component("preferenceConsumer")
public class PreferenceConsumer implements Consumer<KStream<UUID, Preference>> {

    @Override
    public void accept(KStream<UUID, Preference> stream) {
        stream.peek((key, value) -> System.out.printf("key: %s, value: %s\n", key, value));
    }
}
