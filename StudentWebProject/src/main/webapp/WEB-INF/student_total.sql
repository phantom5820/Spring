--------------------------------------------------------
--  파일이 생성됨 - 화요일-2월-02-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table STUDENT
--------------------------------------------------------
drop table student;
select * from student;
  CREATE TABLE "STUDENT" 
   (	"SNO" VARCHAR2(8 BYTE), 
	"NAME" VARCHAR2(30 BYTE), 
	"MAJOR" VARCHAR2(30 BYTE), 
	"SCORE" NUMBER(3,2)
   )
--------------------------------------------------------
--  DDL for Table STUDENT_LOG
--------------------------------------------------------
select * from student_log;
  CREATE TABLE "STUDENT_LOG" 
   (	"LOG_DATE" DATE, 
	"ERROR_CODE" NUMBER, 
	"CONTENT" VARCHAR2(100 BYTE)
   )
REM INSERTING into STUDENT
SET DEFINE OFF;
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('BY6288','김동수','경제',3.44);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('XZ4104','김시우','경제',4.25);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('UB5061','김준래','경제',3.1);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('AJ5374','박셩우','경제',1.42);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('NV2207','박병상','경제',4.39);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('VN8158','박정현','경영',1.87);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('AP5064','서민우','경영',1.43);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('WV3171','박성용','경영',3.95);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('EK6013','이원구','경영',3.71);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('SD8948','정성수','관광',3.93);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('TA1750','정희우','컴퓨터공학',4.02);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('UP6814','조성수','컴퓨터공학',2.73);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('OP4763','주지우','컴퓨터공학',1.71);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('QA2041','최승수','컴퓨터공학',1.78);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('DU1265','한상오','컴퓨터공학',3.86);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('HO7352','이지원','관광',3.02);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('PW9945','김희연','관광',1.94);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('GR5186','노소정','관광',3.28);
Insert into STUDENT (SNO,NAME,MAJOR,SCORE) values ('AY1772','손지해','관광',3.57);
REM INSERTING into STUDENT_LOG
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C007523
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007523" ON "STUDENT" ("SNO")
--------------------------------------------------------
--  Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "STUDENT" ADD PRIMARY KEY ("SNO") ENABLE
