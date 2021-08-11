package com.pax8pro.streamProcessor;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final InteractiveQueryService interactiveQueryService;

    public Controller(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    @GetMapping("/store")
    ResponseEntity<Void> printStore() {
        ReadOnlyKeyValueStore<Object, Object> queryableStore = interactiveQueryService.getQueryableStore("preferences-state-store-v2", QueryableStoreTypes.keyValueStore());
        queryableStore.all().forEachRemaining(i -> System.out.println("key: " + i.key + ", value: " + i.value));
        return ResponseEntity.noContent().build();
    }
}
