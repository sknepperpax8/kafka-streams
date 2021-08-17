package com.pax8pro.streamProcessor.user.stream;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("finalMessageProcessor")
public class FinalMessageProcessor implements Consumer<KStream<String, String>> {

    private final InteractiveQueryService interactiveQueryService;

    public FinalMessageProcessor(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    @Override
    public void accept(KStream<String, String> stringStringKStream) {
        stringStringKStream
                .peek((key, value) -> System.out.printf("key: %s, value: %s%n", key, value))
                .foreach(((key, value) -> {
                        ReadOnlyKeyValueStore<Object, Object> queryableStore = interactiveQueryService.getQueryableStore(value, QueryableStoreTypes.keyValueStore());
                        queryableStore.all().forEachRemaining((v) -> System.out.printf("store:: key: %s, value: %s%n", v.key, v.value));
                }));
    }
}
