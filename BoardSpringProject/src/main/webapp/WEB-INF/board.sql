--게시판 테이블(BOARD)
create table board(
    bno number primary key,
    title varchar2(150 byte),
    bdate date default sysdate,
    bcount number default 0,
    writer varchar2(25 byte),
    content varchar2(500 byte),
    blike number default 0,
    bhate number default 0,
    constraint fk_writer foreign key(writer) references member(id)
);
alter table board add constraint fk_writer foreign key(writer) references member(id);
--댓글
create table board_comment(
    cno number primary key,
    bno number,
    content varchar2(1500 byte),
    cdate date default sysdate,
    writer varchar2(25 byte),
    blike number default 0,
    bhate number default 0,
    constraint fk_comment_writer foreign key(writer) references member(id),
    constraint fk_comment_bno foreign key(bno) references board(bno)
);
--게시판 시퀸스
create sequence bno_seq start with 1 NOMAXVALUE;
--댓글 시퀸스
create sequence cno_seq start with 1 NOMAXVALUE;


--게시판 글목록
select * from board;
select sysdate from dual;
alter session set time_zone = 'Asia/Seoul';


select * from board_comment;

--게시글 목록
select b.*, nvl(c.comment_count,0) 
from board b, (select bno, count(*)as comment_count from board_comment group by bno) c
where b.bno = c.bno(+);

--게시글별 댓글 개수--
select bno, count(*) from board_comment group by bno;

delete from board where writer is null or title is null;

--페이징 : 많은 데이터들 중 순서대로 원하는 양 만큼 원하는 일부분을 조회
select b.*, nvl(c.comment_count,0) 
from board b, (select bno, count(*)as comment_count from board_comment group by bno) c
where b.bno = c.bno(+);

--Step1 정렬 : 정렬기준 날짜 또는 글번호
select b.*, nvl(c.comment_count,0) 
from board b, (select bno, count(*)as comment_count from board_comment group by bno) c
where b.bno = c.bno(+) order by b.bno desc;


-- Step2 처음부터 게시글을 7개만 조회
select rownum,BNO,TITLE,BDATE,BCOUNT,WRITER,CONTENT,BLIKE,BHATE from 
(select b.*, nvl(c.comment_count,0) as comment_count 
from board b, (select bno, count(*) as comment_count 
from board_comment group by bno) c
where b.bno = c.bno(+) order by b.bno desc) where rownum <= 7;

-- Step3 1페이지 : 1~7, 2페이지 8 ~ 14, 3페이지 15~21
-- 위에 결과를 토대로 2페이지에 해당하는 글 목록을 조회
-- 페이지번호 n, 페이지당 보여줄 글목록 개수 x
-- 시작 글 번호 : (n-1) * x + 1
-- 마지막 글 번호 : n * x
select * from(select rownum as rn,BNO,TITLE,BDATE,BCOUNT,WRITER,CONTENT,BLIKE,BHATE from 
(select b.*, nvl(c.comment_count,0) as comment_count 
from board b, (select bno, count(*) as comment_count 
from board_comment group by bno) c
where b.bno = c.bno(+) order by b.bno desc)) where rn between 8 and 14;

--★정답 !!페이지 번호를 구해서 where 간단하게
select * from(select ceil(rownum/7) as pagenum,BNO,
TITLE,BDATE,BCOUNT,WRITER,CONTENT,BLIKE,BHATE 
from (select b.*, nvl(c.comment_count,0) as comment_count from board b, 
(select bno, count(*) as comment_count from board_comment group by bno) c
where b.bno = c.bno(+) order by b.bno desc)) where pagenum = 1;


--이런경우에는 rownum이 1부터 시작인데 조회가 되야 증가, 
select rownum,BNO,TITLE,BDATE,BCOUNT,WRITER,CONTENT,BLIKE,BHATE from 
(select b.*, nvl(c.comment_count,0) as comment_count 
from board b, (select bno, count(*) as comment_count 
from board_comment group by bno) c
where b.bno = c.bno(+) order by b.bno desc) where rownum >= 8;--지금처럼 조건이 거짓이면 조회가 안되기에 rownum이 증가를 안한다!!!



--파일 업로드 해서 업로드된 파일을 저장하기 위한 테이블 (2021-01-17)
CREATE TABLE BOARD_FILE_LIST(
	BNO NUMBER,
	WRITER VARCHAR2(25 BYTE),
	FILENAME VARCHAR2(100 BYTE)
);

-- BOARD_FILE_LIST 외래키 넣기 
alter table BOARD_FILE_LIST add constraint fk_File_bno foreign key(bno) references board(bno) on delete cascade;
--기존에 외래키를 넣어놨다면 외래키가 있다면 (지우고 다시 만든다! 외래키는 수정이 안되기 때문이다)


