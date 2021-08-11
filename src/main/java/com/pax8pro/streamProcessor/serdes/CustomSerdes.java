package com.pax8pro.streamProcessor.serdes;

import com.pax8pro.streamProcessor.user.User;
import com.pax8pro.streamProcessor.preference.Preference;
import com.pax8pro.streamProcessor.preference.UserWithPreference;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public final class CustomSerdes {
    static public final class UserSerde
            extends Serdes.WrapperSerde<User> {
        public UserSerde() {
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(User.class));
        }
    }

    static public final class PreferenceSerde
            extends Serdes.WrapperSerde<Preference> {
        public PreferenceSerde() {
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(Preference.class));
        }
    }

    static public final class UserWithPreferenceSerde
            extends Serdes.WrapperSerde<UserWithPreference> {
        public UserWithPreferenceSerde() {
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(UserWithPreference.class));
        }
    }

    public static Serde<User> User() {
        return new CustomSerdes.UserSerde();
    }

    public static Serde<Preference> Preference() {
        return new CustomSerdes.PreferenceSerde();
    }

    public static Serde<UserWithPreference> UserWithPreference() {
        return new CustomSerdes.UserWithPreferenceSerde();
    }
}
