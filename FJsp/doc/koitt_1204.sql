ALTER TABLE
    member
ADD (
    m_gen char(1)
);

UPDATE
    MEMBER
SET
    m_gen = 'M'
;

UPDATE 
    member
SET
    m_gen = 'F'
WHERE
    m_no = 1005
;

commit;

ALTER TABLE
    member
ADD (
    CONSTRAINT MEMB_GEN_CK CHECK(m_gen IN ('F', 'M'))
);

ALTER TABLE
    member
MODIFY (
    m_gen
    CONSTRAINT MEMB_GEN_NN NOT NULL
);

/*
    프로필 사진 정보 저장 테이블
*/
CREATE TABLE M_PHOTO(
    p_no number(7)
        CONSTRAINT PHOTO_NO_PK PRIMARY KEY,
    p_mno number(4)
        CONSTRAINT PHOTO_MNO_FK REFERENCES member(m_no)
        CONSTRAINT PHOTO_MNO_NN NOT NULL,
    p_oriname varchar2(50 char)
        CONSTRAINT PHOTO_ONAME_NN NOT NULL,
    p_savename varchar2(50 char)
        CONSTRAINT PHOTO_SNAME_UK UNIQUE
        CONSTRAINT PHOTO_SNAME_NN NOT NULL,
    -- 추가되어야 할 컬럼 : 저장일, 삭제여부, 폴더이름, 파일길이, 
    p_len number
        CONSTRAINT PHOTO_LEN_NN NOT NULL,
    p_dir varchar2(500 char)
        CONSTRAINT PHOTO_DIR_NN NOT NULL,
    p_date Date default sysdate
        CONSTRAINT PHOTO_DATE_NN NOT NULL,
    p_isshow char(1) default 'Y'
        CONSTRAINT PHOTO_ISSHOW_CK CHECK(p_isshow IN ('Y', 'N', 'C'))
        CONSTRAINT PHOTO_ISSHOW_NN NOT NULL
);

DROP TABLE M_PHOTO;





    
    
    
    
    