-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- TBL_USER Table Create SQL
CREATE TABLE TBL_USER
(
    ID          NUMBER          NOT NULL,
    NAME        VARCHAR2(20)    NOT NULL,
    LOGINID     VARCHAR2(20)    NOT NULL,
    PASSWORD    VARCHAR2(20)    NOT NULL,
    MOBILE      VARCHAR2(20)    NOT NULL,
    ROLE        VARCHAR2(20)    NOT NULL,
    PRIMARY KEY (ID)
)
    /

CREATE SEQUENCE TBL_USER_SEQ
    START WITH 1
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER TBL_USER_AI_TRG
BEFORE INSERT ON TBL_USER 
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
SELECT TBL_USER_SEQ.NEXTVAL
INTO :NEW.ID
FROM DUAL;
END;
/

--DROP TRIGGER TBL_USER_AI_TRG;
/

--DROP SEQUENCE TBL_USER_SEQ;
/

COMMENT ON TABLE TBL_USER IS '사용자'
/

COMMENT ON COLUMN TBL_USER.ID IS '아이디'
/

COMMENT ON COLUMN TBL_USER.NAME IS '이름'
/

COMMENT ON COLUMN TBL_USER.LOGINID IS '로그인 아이디'
/

COMMENT ON COLUMN TBL_USER.PASSWORD IS '비밀번호'
/

COMMENT ON COLUMN TBL_USER.MOBILE IS '핸드폰 번호'
/

COMMENT ON COLUMN TBL_USER.ROLE IS '역할'
/


