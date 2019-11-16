-------------------------------------------------------------
-- 먼저 데이터베이스 수정
DROP TABLE FILEINFO;
CREATE TABLE fileinfo(
    f_no NUMBER(5)
        CONSTRAINT FI_NO_PK PRIMARY KEY,
    f_fbno NUMBER(5)
        CONSTRAINT FI_FBNO_FK REFERENCES fileboard(fb_no)
        CONSTRAINT FI_FBNO_NN NOT NULL,
    f_oname VARCHAR2(50 CHAR)
        CONSTRAINT FI_ONAME_NN NOT NULL,
    f_sname VARCHAR2(50 CHAR)
        CONSTRAINT FI_SNAME_NN NOT NULL,
    f_dir VARCHAR2(500 CHAR)
        CONSTRAINT FI_DIR_NN NOT NULL,
    f_len NUMBER
        CONSTRAINT FI_LEN_NN NOT NULL,
    f_date DATE default SYSDATE
        CONSTRAINT FI_DATE_NN NOT NULL,
    f_isshow CHAR(1) default 'Y'
        CONSTRAINT FI_ISSHOW_CK CHECK(f_isshow IN('Y', 'N'))
        CONSTRAINT FI_ISSHOW_NN NOT NULL
);
drop table fileboard;
CREATE TABLE fileboard(
    fb_no number(5)
        CONSTRAINT FB_NO_PK PRIMARY KEY,
    fb_writer NUMBER(4)
        CONSTRAINT FB_WRITER_PK REFERENCES member(m_no)
        CONSTRAINT FB_WRITER_NN NOT NULL,
    fb_title VARCHAR2(100 CHAR)
        CONSTRAINT FB_TTL_NN NOT NULL,
    fb_body VARCHAR2(4000)
        CONSTRAINT FB_BD_NN NOT NULL,
    fb_wdate DATE default sysdate
        CONSTRAINT FB_DATE_NN NOT NULL,
    fb_isshow CHAR(1) default 'Y'
        CONSTRAINT FB_ISSHOW_CK CHECK(fb_isshow IN('Y', 'N'))
        CONSTRAINT FB_ISSHOW_NN NOT NULL
);

--------------------------------------------------------------

INSERT INTO 
    member(
        m_no, m_id, m_pw, m_name, m_mail, m_tel
    )
VALUES(
    1001, 'euns', '12345', '전은석', 'euns_jun@naver.com', '010-3175-9042'
);

INSERT INTO
    fileinfo(
        f_no, f_fbno, f_oname, f_sname, f_dir, f_len
    )
VALUES(
    10001, 10001, 'test.jpg', 'test.jpg', '/upload', 1234
);

commit;

SELECT
    MAX(fb_wdate)
FROM
    fileboard
WHERE
    fb_writer = 1001
;