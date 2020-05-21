package com.staffmanagement.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomScheduler {
	private final Logger log = LoggerFactory.getLogger(CustomScheduler.class);

	private ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
	private int limit;

	public void processRequest(int lim) {
		limit = lim;
		scheduledThreadPool.scheduleWithFixedDelay(() -> print(), 0, 10, TimeUnit.SECONDS);
	}

	private void print() {
		log.info("Task scheduled at : {} , Hello {}", LocalDateTime.now(), limit);
		limit--;
		if (limit == 0) {
			scheduledThreadPool.shutdown();
		}
	}
}
