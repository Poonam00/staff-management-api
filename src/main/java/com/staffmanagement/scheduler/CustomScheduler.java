package com.staffmanagement.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomScheduler {

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

