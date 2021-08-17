package com.pax8pro.streamProcessor.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private UUID id;
    private String name;
    private Boolean lastMessage;
}
