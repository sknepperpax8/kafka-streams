package com.pax8pro.streamProcessor.preference;

import com.pax8pro.streamProcessor.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithPreference {
    private User user;
    private Boolean ignore;
}
