package com.pax8pro.streamProcessor.preference.stream;

import com.pax8pro.streamProcessor.preference.Preference;
import lombok.AllArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@AllArgsConstructor
@Component("preferenceProcessor")
public class PreferenceProcessor implements Function<KStream<String, Preference>, KStream<UUID, Preference>> {

    @Override
    public KStream<UUID, Preference> apply(KStream<String, Preference> preferenceStream) {
        return preferenceStream.map((k, v) -> new KeyValue<>(v.getUserId(), v), Named.as("preferences-mapped"));
    }
}
