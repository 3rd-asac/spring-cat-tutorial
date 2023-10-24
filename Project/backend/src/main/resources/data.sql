-- USER 테이블
DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
                      id int unsigned NOT NULL AUTO_INCREMENT,
                      email varchar(50) NOT NULL,
                      password varchar(200) NOT NULL,
                      name varchar(50),
                      created timestamp DEFAULT CURRENT_TIMESTAMP,
                      updated timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      status varchar(10) DEFAULT 'ACTIVE',
                      CONSTRAINT USER_PK PRIMARY KEY(id)
);

-- 예제 데이터 추가
INSERT INTO USER(email, password, name) VALUES('email1@naver.com', '1234', 'name1');
INSERT INTO USER(email, password, name) VALUES('email2@naver.com', '1234', 'name2');
INSERT INTO USER(email, password, name) VALUES('email3@naver.com', '1234', 'name3');

-- MEMO 테이블
DROP TABLE IF EXISTS MEMO;
CREATE TABLE MEMO (
                      id int unsigned NOT NULL AUTO_INCREMENT,
                      memo varchar(100) NOT NULL,
                      created timestamp DEFAULT CURRENT_TIMESTAMP,
                      updated timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      state varchar(10) DEFAULT 'ACTIVE',
                      CONSTRAINT TEST_PK PRIMARY KEY(id)
);