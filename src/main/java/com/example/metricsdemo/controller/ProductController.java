package com.example.metricsdemo.controller;

import com.example.metricsdemo.service.MetricsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final MetricsService metricsService;

    public ProductController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/view")
    public String viewProduct(@RequestParam(defaultValue = "P001") String id) {
        long start = System.currentTimeMillis();

        // Simulate work
        try {
            Thread.sleep(100 + (int)(Math.random() * 300));
        } catch (InterruptedException ignored) {}

        metricsService.recordProductView(id);
        metricsService.recordApiRequestTime(System.currentTimeMillis() - start);

        return "Product " + id + " viewed successfully!";
    }
}
