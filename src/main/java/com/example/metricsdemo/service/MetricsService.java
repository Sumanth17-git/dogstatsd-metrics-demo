package com.example.metricsdemo.service;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final StatsDClient statsd = new NonBlockingStatsDClient(
            "springboot",  // Metric prefix
            "localhost", 8125 // DogStatsD listener (Datadog agent)
    );

    public void recordProductView(String productId) {
        statsd.incrementCounter("product.views.count", "product:" + productId, "env:dev");
    }

    public void recordApiRequestTime(long durationInMs) {
        statsd.recordExecutionTime("api.request.time", durationInMs, "env:dev");
    }
}
