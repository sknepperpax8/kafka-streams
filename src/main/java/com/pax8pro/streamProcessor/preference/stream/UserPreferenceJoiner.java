package com.pax8pro.streamProcessor.preference.stream;

import com.pax8pro.streamProcessor.preference.domain.Preference;
import com.pax8pro.streamProcessor.preference.domain.UserWithPreference;
import com.pax8pro.streamProcessor.user.domain.User;
import org.apache.kafka.streams.kstream.ValueJoiner;

public class UserPreferenceJoiner implements ValueJoiner<User, Preference, UserWithPreference> {

    @Override
    public UserWithPreference apply(User user, Preference preference) {
        final Boolean ignore =  preference != null ? preference.getIgnore() : false;

        return new UserWithPreference(user, ignore);
    }
}
