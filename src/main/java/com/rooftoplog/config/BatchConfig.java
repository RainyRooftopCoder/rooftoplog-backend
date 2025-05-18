package com.rooftoplog.config;

import com.rooftoplog.entity.PostEntity;
import com.rooftoplog.listener.JobLoggerListener;
import com.rooftoplog.listener.StepLoggerListener;
import com.rooftoplog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PostService postService;

    @Bean
    public Job sampleJob(JobLoggerListener jobLoggerListener, StepLoggerListener listener) {
        return jobBuilderFactory.get("sampleJob")
                .listener(jobLoggerListener)  // <-- 여기가 핵심
                .start(sampleStep(listener))
                .build();
    }

    @Bean
    public Step sampleStep(StepLoggerListener listener) {
        return stepBuilderFactory.get("sampleStep")
                .listener(listener)
                .tasklet(sampleTasklet())
                .build();
    }

    @Bean
    public Tasklet sampleTasklet() {
        return (contribution, chunkContext) -> {
            log.info("🕒 [배치 작업 실행됨] 시간: {}", LocalDateTime.now());
            log.info("      ⮕ [TASKLET 실행] 게시글 데이터 조회 및 출력");

            // 실제 처리 로직 삽입 위치
            List<PostEntity> posts = postService.getPosts("tester");

            posts.forEach(post -> {
                log.debug("        └ postId={}, title={}, createdAt={}",
                        post.getPostId(), post.getTitle(), post.getCreatedAt());
            });

            log.info("✅ TASKLET 실행 완료 - sampleStep");
            return RepeatStatus.FINISHED;
        };
    }

}
