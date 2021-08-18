package com.pax8pro.streamProcessor;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InteractiveQueryController {

    private final InteractiveQueryService interactiveQueryService;

    public InteractiveQueryController(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    @GetMapping("/store")
    ResponseEntity<Map<Object, Object>> displayStoreByName(@RequestParam(defaultValue = "preferences-state-store") String storeName) {
        ReadOnlyKeyValueStore<Object, Object> queryableStore = interactiveQueryService.getQueryableStore(storeName, QueryableStoreTypes.keyValueStore());
        Map<Object, Object> map = new HashMap<>();
        queryableStore.all().forEachRemaining(i -> map.put(i.key, i.value));
        return ResponseEntity.ok(map);
    }
}
