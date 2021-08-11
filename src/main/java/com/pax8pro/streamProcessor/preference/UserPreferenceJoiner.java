package com.pax8pro.streamProcessor.preference;

import com.pax8pro.streamProcessor.user.User;
import org.apache.kafka.streams.kstream.ValueJoiner;

public class UserPreferenceJoiner implements ValueJoiner<User, Preference, UserWithPreference> {
    @Override
    public UserWithPreference apply(User user, Preference preference) {
        final Boolean ignore =  preference != null ? preference.getIgnore() : false;

        return new UserWithPreference(user, ignore);
    }
}
