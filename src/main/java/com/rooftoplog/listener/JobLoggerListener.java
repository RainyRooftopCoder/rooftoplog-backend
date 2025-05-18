package com.rooftoplog.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobLoggerListener  implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("💚💚 Job 시작: {}", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("🔚 Job 종료: {}, 상태: {}",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getStatus());
    }
}
