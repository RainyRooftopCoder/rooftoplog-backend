package com.rooftoplog.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher jobLauncher;
    private final Job testJob;

    @Scheduled(cron = "*/10 * * * * *") // 10초마다 실행
    /*
    초(Second) 분(Minute) 시(Hour) 일(Day of Month) 월(Month) 요일(Day of Week)
    ┌───── 초 (0–59)
    │ ┌───── 분 (0–59)
    │ │ ┌───── 시 (0–23)
    │ │ │ ┌───── 일 (1–31)
    │ │ │ │ ┌───── 월 (1–12 또는 JAN–DEC)
    │ │ │ │ │ ┌───── 요일 (0–6 또는 SUN–SAT, 0=일요일)
    │ │ │ │ │ │
    │ │ │ │ │ │
    * * * * * *

    `cron`       | 지정된 시간대(예: 매일 08:00)에 실행
    `fixedRate`  | 이전 실행 시점 기준으로 주기적으로 실행됨
    `fixedDelay` | 이전 실행 **완료** 후부터 주기적 실행

    @Scheduled(fixedRate = 8 * 60 * 60 * 1000) // 8시간마다 반복
    */
    public void runJob() {
        log.info("🔔 스케줄러 트리거됨");

        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // JobInstance 구분용
                    .toJobParameters();

            log.info("🚀 배치 작업 실행 요청 시작");
            jobLauncher.run(testJob, params);
            log.info("✅ 배치 작업 실행 요청 완료");

        } catch (Exception e) {
            log.error("❌ 배치 실행 중 예외 발생: {}", e.getMessage());
        }
    }

}
