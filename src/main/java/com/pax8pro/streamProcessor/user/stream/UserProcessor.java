package com.pax8pro.streamProcessor.user.stream;

import com.pax8pro.streamProcessor.preference.Preference;
import com.pax8pro.streamProcessor.preference.UserPreferenceJoiner;
import com.pax8pro.streamProcessor.serdes.CustomSerdes;
import com.pax8pro.streamProcessor.user.User;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@AllArgsConstructor
@Component("userProcessor")
public class UserProcessor implements BiFunction<KStream<String, User>, GlobalKTable<UUID, Preference>, KStream<String, String>> {

    @Override
    public KStream<String, String> apply(KStream<String, User> userStream, GlobalKTable<UUID, Preference> preferencesTable) {
        String storeName = aggregate(userStream, preferencesTable);

        return userStream
                    .filter((key, value) -> value.getLastMessage())
                    .map((key, value) -> new KeyValue<>(key, storeName));
    }

    private String aggregate(KStream<String, User> userStream, GlobalKTable<UUID, Preference> preferencesTable) {
        return userStream
            .leftJoin(preferencesTable, (key, user) -> user.getId(), new UserPreferenceJoiner())
            .filter((key, userWithPreference) -> !userWithPreference.getIgnore())
            .groupBy((key, userWithPreference) -> userWithPreference.getUser().getName(), Grouped.with(Serdes.String(), CustomSerdes.UserWithPreference()))
            .count(Materialized.as("count-store"))
            .queryableStoreName();
    }
}
