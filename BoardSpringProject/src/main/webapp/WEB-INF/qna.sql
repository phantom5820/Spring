create table qna(
	pno number primary key,
	title varchar2(100 byte),
	content varchar2(4000 byte),
	wdate date default sysdate,
	writer varchar2(25 byte),
	status number default 0, --않읽음 - 0, 읽음 -1 , 답변완료 - 2
	response varchar2(4000 byte)
);

create sequence qno_seq start with 1;

select pno, title, content from QNA;

select * from MEMBER;
SELECT * FROM USER_SEQUENCES;
update member set grade = '1' where name like '홍길동';

alter table qna rename column pno to qno;
select * from qna;