package com.pax8pro.streamProcessor.user.stream;

import com.pax8pro.streamProcessor.preference.Preference;
import com.pax8pro.streamProcessor.preference.UserPreferenceJoiner;
import com.pax8pro.streamProcessor.serdes.CustomSerdes;
import com.pax8pro.streamProcessor.user.User;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component("userProcessor")
@AllArgsConstructor
public class UserProcessor implements BiFunction<KStream<UUID, User>, GlobalKTable<UUID, Preference>, KStream<String, Long>> {

    @Override
    public KStream<String, Long> apply(KStream<UUID, User> userStream, GlobalKTable<UUID, Preference> preferencesTable) {
        return userStream
                .leftJoin(preferencesTable, (userId, user) -> user.getId(), new UserPreferenceJoiner())
                .filter((userId, userWithPreference) -> !userWithPreference.getIgnore())
                .groupBy((userId, userWithPreference) -> userWithPreference.getUser().getName(), Grouped.with(Serdes.String(), CustomSerdes.UserWithPreference()))
                .count()
                .toStream();
    }
}