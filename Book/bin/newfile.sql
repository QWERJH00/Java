
/* Drop Triggers */

DROP TRIGGER TRI_BOOKDB_book_number;
DROP TRIGGER TRI_MEMBERDB_member_number;



/* Drop Tables */

DROP TABLE RENTDB CASCADE CONSTRAINTS;
DROP TABLE BOOKDB CASCADE CONSTRAINTS;
DROP TABLE MEMBERDB CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_BOOKDB_book_number;
DROP SEQUENCE SEQ_MEMBERDB_member_number;




/* Create Sequences */

CREATE SEQUENCE SEQ_BOOKDB_book_number INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_MEMBERDB_member_number INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE BOOKDB
(
	book_name varchar2(30),
	book_count varchar2(30) NOT NULL,
	book_number number NOT NULL,
	PRIMARY KEY (book_number)
);


CREATE TABLE MEMBERDB
(
	member_id varchar2(30) NOT NULL,
	member_pw varchar2(30) NOT NULL,
	member_name varchar2(30) NOT NULL,
	member_number number NOT NULL,
	PRIMARY KEY (member_id, member_number)
);


CREATE TABLE RENTDB
(
	member_id varchar2(30) NOT NULL,
	book_number number NOT NULL,
	rent_date varchar2(30) NOT NULL,
	return_date varchar2(30) NOT NULL,
	member_number number NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE RENTDB
	ADD FOREIGN KEY (book_number)
	REFERENCES BOOKDB (book_number)
;


ALTER TABLE RENTDB
	ADD FOREIGN KEY (member_id, member_number)
	REFERENCES MEMBERDB (member_id, member_number)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_BOOKDB_book_number BEFORE INSERT ON BOOKDB
FOR EACH ROW
BEGIN
	SELECT SEQ_BOOKDB_book_number.nextval
	INTO :new.book_number
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_MEMBERDB_member_number BEFORE INSERT ON MEMBERDB
FOR EACH ROW
BEGIN
	SELECT SEQ_MEMBERDB_member_number.nextval
	INTO :new.member_number
	FROM dual;
END;

/




