package com.pax8pro.streamProcessor.preference.domain;

import com.pax8pro.streamProcessor.user.domain.User;
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
