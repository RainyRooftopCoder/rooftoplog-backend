CREATE TABLE BATCH_JOB_INSTANCE (
                                    JOB_INSTANCE_ID INTEGER PRIMARY KEY,
                                    VERSION INTEGER,
                                    JOB_NAME VARCHAR(100) NOT NULL,
                                    JOB_KEY VARCHAR(32) NOT NULL,
                                    constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
);

CREATE TABLE BATCH_JOB_EXECUTION (
                                     JOB_EXECUTION_ID INTEGER PRIMARY KEY,
                                     VERSION INTEGER,
                                     JOB_INSTANCE_ID INTEGER NOT NULL,
                                     CREATE_TIME TIMESTAMP NOT NULL,
                                     START_TIME TIMESTAMP DEFAULT NULL,
                                     END_TIME TIMESTAMP DEFAULT NULL,
                                     STATUS VARCHAR(10),
                                     EXIT_CODE VARCHAR(20),
                                     EXIT_MESSAGE VARCHAR(2500),
                                     LAST_UPDATED TIMESTAMP,
                                     JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL
);

-- (다른 BATCH_* 테이블도 필요함. 원하시면 전체 제공 가능)
