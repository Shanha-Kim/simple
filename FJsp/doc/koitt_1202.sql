CREATE TABLE
    avatar
(
    a_no number(2)
        CONSTRAINT AVT_NO_PK PRIMARY KEY,
    a_gen char(1)
        CONSTRAINT AVT_GEN_CK CHECK( a_gen IN ('F', 'M'))
        CONSTRAINT AVT_GEN_NN NOT NULL,
    a_img varchar2(30 char)
        CONSTRAINT AVT_IMG_UK UNIQUE
        CONSTRAINT AVT_IMG_NN NOT NULL
);

INSERT INTO AVATAR
VALUES(
    11, 'M', 'img_avatar1.png'
);

INSERT INTO AVATAR
VALUES(
    12, 'M', 'img_avatar2.png'
);
INSERT INTO AVATAR
VALUES(
    13, 'M', 'img_avatar3.png'
);
INSERT INTO AVATAR
VALUES(
    14, 'M', 'img_avatar4.png'
);
INSERT INTO AVATAR
VALUES(
    15, 'M', 'img_avatar5.png'
);
INSERT INTO AVATAR
VALUES(
    16, 'M', 'img_avatar6.png'
);

ALTER TABLE
    member
ADD (
    m_avt number(2) default 11
        CONSTRAINT MEMB_AVT_FK REFERENCES avatar(a_no)
        CONSTRAINT MEMB_AVT_NN NOT NULL
);


--------------------------------
alter table
    fileboard
drop column fb_fno;

alter table fileinfo
add (
    f_bno number(5)
        CONSTRAINT FI_BNO_FK REFERENCES fileboard(fb_no)
        CONSTRAINT FI_BNO_NN NOT NULL
);

------------------------------------------------
CREATE TABLE reboard(
    rb_no number(5)
        CONSTRAINT RB_NO_PK PRIMARY KEY,
    rb_mno number(4)
        CONSTRAINT RB_MNO_FK REFERENCES member(m_no)
        CONSTRAINT RB_MNO_NN NOT NULL,
    rb_body varchar2(4000)
        CONSTRAINT RB_BODY_NN NOT NULL,
    rb_date DATE default sysdate
        CONSTRAINT RB_DATE_NN NOT NULL,
    rb_upno number(5)
        CONSTRAINT RB_UPNO_FK REFERENCES reboard(rb_no),
    rb_isshow char(1) default 'Y'
        CONSTRAINT RB_ISSHOW_CK CHECK (rb_isshow IN ('Y', 'N'))
        CONSTRAINT RB_ISSHOW_NN NOT NULL
);

INSERT INTO
    reboard(rb_no, rb_mno, rb_body)
VALUES(
    10001, 1001, 'TEST'
);
INSERT INTO
    reboard(rb_no, rb_mno, rb_body, rb_upno)
VALUES(
    10002, 1002, 'REBODY..', 10001
);


COMMIT;


--------------------------------------------------------------------
-- 10001번글의 정보를 가져오자.
-- 아바타 이미지, 작성자아이디, 글번호, 본문, 작성일

-- join 으로 처리하기

SELECT
    M_ID, A_IMG, RB_NO, RB_BODY, RB_DATE
FROM
    MEMBER m, AVATAR a, REBOARD r
WHERE
    RB_ISSHOW = 'Y' -- 일반조건
    AND m.m_no = rb_mno -- JOIN 조건
    AND m.m_avt = a.a_no    -- 조인조건
    AND r.rb_no = 10001 -- 일반조건
;

create table ecolor(
    ecno number(2)
        constraint eclr_no_pk PRIMARY KEY,
    ec_name varchar2(20 char)
        constraint eclr_name_uk unique
        constraint eclr_name_nn not null
);
create table kcolor(
    kcno number(2)
        constraint kclr_no_pk PRIMARY KEY,
    kc_name varchar2(20 char)
        constraint kclr_name_uk unique
        constraint kclr_name_nn not null
);

INSERT INTO ECOLOR VALUES( 10, 'RED');
INSERT INTO ECOLOR VALUES( 11, 'BLUE');
INSERT INTO ECOLOR VALUES( 12, 'GREEN' );

INSERT INTO KCOLOR VALUES(10, '빨강');
INSERT INTO KCOLOR VALUES( 11, '파랑' );
INSERT INTO KCOLOR VALUES(12, '초록');

-- 칼라코드와 영어이름, 한글이름을 조회하려고 한다.
SELECT
    *
FROM
    ECOLOR, KCOLOR
WHERE
    ECNO = KCNO
;

--------------------------------------------
SELECT
    (
        SELECT
            M_ID
        FROM
            MEMBER
        WHERE
            M_NO = RB_MNO
    ) MID, 
    (
        SELECT
            A_IMG
        FROM
            AVATAR
        WHERE
            A_NO = (
                        SELECT
                            M_AVT
                        FROM
                            MEMBER
                        WHERE
                            M_NO = RB_MNO
                    )
    ) AVT, 
    RB_NO, RB_BODY, RB_DATE
FROM
    REBOARD
WHERE
    RB_ISSHOW = 'Y' -- 일반조건
;

-- 댓글게시판 테이블에서 회원번호를 이용해서 
-- 회원아이디와 회원 아바타 이름(파일이름)을 
-- 조회하는 질의명령을 작성하세요.

-- 회원테이블의 회원 아이디와 
-- 회원의 아바타 파일이름을 조회하세요.
SELECT
    ROWNUM RNO, m_id, avt, rb_no, rb_body, 
    rb_date, rb_upno, (level - 1) lvl
FROM
    (
    SELECT
        m_id, avt, rb_no, rb_body, rb_date, rb_upno
    FROM
        reboard,
        (SELECT
            m_no, m_id,
            (
                SELECT
                    a_img
                FROM
                    avatar
                WHERE
                    a_no = m_avt
            ) avt
        FROM
            member
        )
    WHERE
        RB_ISSHOW = 'Y'
        AND m_no = rb_mno
    )
START WITH
    RB_UPNO IS NULL
CONNECT BY
    PRIOR RB_NO = RB_UPNO
ORDER SIBLINGS BY
    RB_DATE DESC
;

COMMIT;
