-- 사용자 테이블
CREATE TABLE USER (
                      USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,  -- 사용자 ID
                      USERNAME TEXT NOT NULL UNIQUE,               -- 사용자 계정명 (최대 50자 권장)
                      PASSWORD TEXT NOT NULL,                      -- 비밀번호 (암호화 저장, 255자)
                      ROLE TEXT NOT NULL DEFAULT 'ADMIN',          -- 권한 (ADMIN, USER)
                      CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일시
                      UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP -- 수정일시
);

-- 행동 로그 테이블
CREATE TABLE LOG (
                     LOG_ID INTEGER PRIMARY KEY AUTOINCREMENT,    -- 로그 ID
                     USER_ID INTEGER NOT NULL,                    -- 사용자 ID
                     ACTION TEXT NOT NULL,                        -- 작업 명 (ex: LOGIN, POST_CREATE)
                     DESCRIPTION TEXT,                            -- 상세 설명
                     IP_ADDRESS TEXT,                             -- 접속 IP (최대 45자, IPv6)
                     CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일시
                     UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 수정일시
                     FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID)
);

-- 게시판 테이블
CREATE TABLE BOARD (
                       BOARD_ID INTEGER PRIMARY KEY AUTOINCREMENT,   -- 게시판 ID
                       NAME TEXT NOT NULL,                           -- 게시판명 (최대 100자)
                       DESCRIPTION TEXT,                             -- 설명
                       IS_ACTIVE BOOLEAN DEFAULT 1,                  -- 활성화 여부 (1: 사용, 0: 미사용)
                       CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일시
                       UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP  -- 수정일시
);

-- 게시글 테이블
CREATE TABLE POST (
                      POST_ID INTEGER PRIMARY KEY AUTOINCREMENT,     -- 게시글 ID
                      BOARD_ID INTEGER NOT NULL,                     -- 게시판 ID
                      TITLE TEXT NOT NULL,                           -- 제목 (최대 200자)
                      CONTENT TEXT NOT NULL,                         -- 본문 내용
                      AUTHOR TEXT NOT NULL,                          -- 작성자명 (최대 50자)
                      IS_NOTICE BOOLEAN DEFAULT 0,                   -- 공지 여부
                      IS_DELETED BOOLEAN DEFAULT 0,                  -- 삭제 여부
                      FILE_GROUP_ID INTEGER,                         -- 파일 그룹 ID
                      CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일시
                      UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 수정일시
                      FOREIGN KEY (BOARD_ID) REFERENCES BOARD (BOARD_ID),
                      FOREIGN KEY (FILE_GROUP_ID) REFERENCES FILE_GROUP (FILE_GROUP_ID)
);

-- 댓글 테이블
CREATE TABLE COMMENT (
                         COMMENT_ID INTEGER PRIMARY KEY AUTOINCREMENT,  -- 댓글 ID
                         POST_ID INTEGER NOT NULL,                      -- 게시글 ID
                         AUTHOR TEXT NOT NULL,                          -- 작성자명 (최대 50자)
                         CONTENT TEXT NOT NULL,                         -- 댓글 내용
                         IS_DELETED BOOLEAN DEFAULT 0,                  -- 삭제 여부
                         CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일시
                         UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP, -- 수정일시
                         FOREIGN KEY (POST_ID) REFERENCES POST (POST_ID)
);

-- 방명록 테이블
CREATE TABLE GUESTBOOK (
                           GUESTBOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT, -- 방명록 ID
                           AUTHOR TEXT NOT NULL,                           -- 작성자명 (최대 50자)
                           CONTENT TEXT NOT NULL,                          -- 내용
                           IS_DELETED BOOLEAN DEFAULT 0,                   -- 삭제 여부
                           CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 생성일시
                           UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP   -- 수정일시
);

-- 파일 그룹 테이블
CREATE TABLE FILE_GROUP (
                            FILE_GROUP_ID INTEGER PRIMARY KEY AUTOINCREMENT, -- 파일 그룹 ID
                            GROUP_NAME TEXT,                                 -- 그룹명 (최대 100자)
                            DESCRIPTION TEXT,                                -- 설명
                            CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 생성일시
                            UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP    -- 수정일시
);

-- 파일 상세 테이블
CREATE TABLE FILE_DETAIL (
                             FILE_DETAIL_ID INTEGER PRIMARY KEY AUTOINCREMENT, -- 파일 상세 ID
                             FILE_GROUP_ID INTEGER NOT NULL,                   -- 파일 그룹 ID
                             ORIGINAL_FILE_NAME TEXT NOT NULL,                 -- 원본 파일명 (최대 255자)
                             STORED_FILE_NAME TEXT NOT NULL,                   -- 저장 파일명 (UUID 등)
                             FILE_PATH TEXT NOT NULL,                          -- 파일 저장 경로
                             FILE_SIZE INTEGER,                                -- 파일 크기 (byte)
                             CONTENT_TYPE TEXT,                                -- MIME 타입 (최대 100자)
                             DOWNLOAD_COUNT INTEGER DEFAULT 0,                 -- 다운로드 수
                             IS_DELETED BOOLEAN DEFAULT 0,                     -- 삭제 여부
                             CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,    -- 생성일시
                             UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,    -- 수정일시
                             FOREIGN KEY (FILE_GROUP_ID) REFERENCES FILE_GROUP (FILE_GROUP_ID)
);
