-- 설문지 테이블

CREATE TABLE survey (
    s_no number(4)
        CONSTRAINT SRV_NO_PK PRIMARY KEY,
    s_title varchar2(200 char)
        CONSTRAINT SRV_TTL_NN NOT NULL,
    s_start Date
        CONSTRAINT SRV_SDATE_NN NOT NULL,
    s_end Date
        CONSTRAINT SRV_EDATE_NN NOT NULL
);

-- 설문 문항 테이블
CREATE TABLE SRV_QUEST (
    sq_no number(5)
        CONSTRAINT SQ_NO_PK PRIMARY KEY,
    sq_sno number(4)
        CONSTRAINT SQ_SNO_FK REFERENCES survey(s_no)
        CONSTRAINT SQ_SNO_NN NOT NULL,
    sq_cont VARCHAR2(200 CHAR)
        CONSTRAINT SQ_CONT_NN NOT NULL
);

-- 설문 보기 테이블
CREATE TABLE SRV_EX (
    se_no number(6)
        CONSTRAINT SE_NO_PK PRIMARY KEY,
    se_qno number(5)
        CONSTRAINT SE_QNO_FK REFERENCES srv_quest(sq_no)
        CONSTRAINT SE_QNO_NN NOT NULL,
    se_body VARCHAR2(200 CHAR)
        CONSTRAINT SE_BODY_NN NOT NULL,
    se_count NUMBER(3) default 0
        CONSTRAINT SE_CNT_NN NOT NULL
);

-- 설문 참여 테이블
CREATE TABLE SRV_CK (
    sck_no NUMBER(7)
        CONSTRAINT SCK_NO_PK PRIMARY KEY,
    sck_sno NUMBER(4)
        CONSTRAINT SCK_SNO_FK REFERENCES survey(s_no)
        CONSTRAINT SCK_SNO_NN NOT NULL,
    sck_mid varchar2(10 char)
        CONSTRAINT SCK_MID_FK REFERENCES member(m_id)
        CONSTRAINT SCK_MID_NN NOT NULL,
    sck_date Date default sysdate
        CONSTRAINT SCK_DATE_NN NOT NULL
);


-- 설문 데이터 입력
INSERT INTO
    survey
VALUES(
    (SELECT NVL(MAX(S_NO) + 1, 1001) FROM survey),
    'K-POP 선호도 설문 조사', '2019/12/01', '2019/12/07'
);

-- 설문 문항 데이터 입력
INSERT INTO
    srv_quest
VALUES(
    (SELECT NVL(MAX(sq_no) + 1, 10001) FROM srv_quest),
    1001, '당신이 제일 좋아하는 가수는?'
);

INSERT INTO
    srv_quest
VALUES(
    (SELECT NVL(MAX(sq_no) + 1, 10001) FROM srv_quest),
    1001, '당신이 제일 좋아하는 K-POP 노래는?'
);

-- 설문 보기 입력

-- 10001 보기
INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10001, '아이유', 0
);

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10001, 'JFla', 0
);

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10001, 'EXO', 0
);

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10001, 'BTS', 0
);

-- 10002 보기 입력

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10002, '좋은날', 0
);

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10002, '불장난', 0
);

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10002, '가시나', 0
);

INSERT INTO
    srv_ex
VALUES(
    (SELECT NVL(MAX(se_no) + 1, 100001) FROM srv_ex),
    10002, '봄날', 0
);

commit;



SELECT
    s_no
FROM 
    survey
WHERE
    sysdate BETWEEN s_start AND s_end
;

SELECT
    s_no sno, s_title title, 
    s_start startdate, s_end enddate, 
    sq_no qno, sq_cont body
FROM
    survey, srv_quest
WHERE
    s_no = 1001
    AND s_no = sq_sno
;


SELECT
    s_no sno, s_title title,
    s_start startdate, s_end enddate,
    decode(
            (
                SELECT
                    COUNT(*) CNT
                FROM
                    SRV_CK
                WHERE
                    SCK_SNO = s_no
                    AND SCK_MID = 'euns'
            ), 0, 'N',
            'Y') COUNT
FROM
    survey
WHERE
    sysdate BETWEEN s_start AND s_end
;

SELECT
    *
FROM
    SURVEY, SRV_CK
;

SELECT
    COUNT(*) CNT
FROM
    SRV_CK
WHERE
    SCK_SNO = 1001
;

-- 이 질의명령은 설문번호를 선택한 후 
-- 그 설문 번호로 데이터를 조회하는 질의명령
-- 꺼내올 데이터는 설문번호, 설문문항번호, 설문문항내용, 설문보기번호, 설문보기내용
SELECT
    sq_sno sno, sq_no qno, sq_cont body, se_no seno, se_body ebody
FROM
    srv_quest, srv_ex
WHERE
    sq_no = se_qno -- 조인조건
    AND sq_sno = 1001
    --AND sq_no = 10001
;

SELECT
    sq_no qno
FROM
    srv_quest
WHERE
    sq_sno = 1001
;










