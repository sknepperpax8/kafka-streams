package com.pax8pro.streamProcessor.user.stream;

import com.pax8pro.streamProcessor.preference.domain.Preference;
import com.pax8pro.streamProcessor.preference.stream.UserPreferenceJoiner;
import com.pax8pro.streamProcessor.preference.domain.UserWithPreference;
import com.pax8pro.streamProcessor.user.domain.User;
import lombok.AllArgsConstructor;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@AllArgsConstructor
@Component("userProcessor")
public class UserProcessor implements BiFunction<KStream<String, User>, GlobalKTable<UUID, Preference>, KStream<String, UserWithPreference>> {

    @Override
    public KStream<String, UserWithPreference> apply(KStream<String, User> userStream, GlobalKTable<UUID, Preference> preferencesTable) {
        return userStream
                .leftJoin(preferencesTable, (key, user) -> user.getId(), new UserPreferenceJoiner())
                .filterNot((key, userWithPreference) -> userWithPreference.getIgnore());
    }
}
