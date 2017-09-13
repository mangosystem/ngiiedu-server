
/* Drop Tables */

DROP TABLE IF EXISTS authkeys;
DROP TABLE IF EXISTS bbs_faq;
DROP TABLE IF EXISTS bbs_pds;
DROP TABLE IF EXISTS bbs_qna;
DROP TABLE IF EXISTS codes;
DROP TABLE IF EXISTS modules_work_comp;
DROP TABLE IF EXISTS comps;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS works_output;
DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS modules_work;
DROP TABLE IF EXISTS modules_work_output;
DROP TABLE IF EXISTS modules;
DROP TABLE IF EXISTS schools;
DROP TABLE IF EXISTS users;




/* Create Tables */

-- 팀인증키
CREATE TABLE authkeys
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 인증키
	aythkey char(6) NOT NULL UNIQUE,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- FAQ
CREATE TABLE bbs_faq
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 자료실
CREATE TABLE bbs_pds
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- Q&A
CREATE TABLE bbs_qna
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 코드테이블
CREATE TABLE codes
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 구분
	division char(1),
	-- 대분류
	dep1 varchar,
	-- 중분류
	dep2 varchar,
	-- 소분류
	dep3 varchar,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 공통검포넌트
CREATE TABLE comps
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 컴포넌트명
	comp_name varchar,
	-- 메타데이터
	comp_metadata varchar,
	-- 썸네일
	comp_thumbnail varchar,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 수업
CREATE TABLE courses
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 모듈_아이디
	module_id int NOT NULL,
	-- 수업명
	course_name varchar,
	-- 수업설명
	course_desc varchar,
	-- 생성자
	course_create_id varchar,
	-- 생성일
	course_create_date char(8),
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 팀원
CREATE TABLE members
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 팀상태
	team_status char(1),
	-- 팀_아이디
	team_id int NOT NULL,
	-- 사용자_아이디
	user_id int NOT NULL,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 모듈
CREATE TABLE modules
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 모듈명
	module_name varchar,
	-- 생성일
	module_create_date varchar,
	-- 수정일
	module_modify_date varchar,
	-- 메타데이터
	module_metadata varchar,
	-- 데이터
	module_data varchar,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 모듈과정
CREATE TABLE modules_work
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 모듈아이디
	module_id int NOT NULL,
	-- 과정명
	module_work_name varchar,
	-- 과정메타데이터
	module_work_metadata varchar,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 모듈과정컴포넌트
CREATE TABLE modules_work_comp
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 모듈과정_아이디
	module_work_id int NOT NULL,
	-- 공통컴포넌트_아이디
	comps_id int NOT NULL,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 모듈과정자료
CREATE TABLE modules_work_data
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 모듈_아이디
	module_id int NOT NULL,
	-- 자료명
	module_work_data_name varchar,
	-- 자료위치경로
	module_work_data_path varchar,
	-- 수업데이터구분
	module_work_data_division char(1),
	-- 수업차수정보
	module_work_seq varchar,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 학교
CREATE TABLE schools
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 학교아이디
	school_id varchar,
	-- 학교명
	school_name varchar,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 팀
CREATE TABLE teams
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 수업과정_아이디
	work_id int NOT NULL,
	-- 인증키
	authkey varchar(6),
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 사용자
CREATE TABLE users
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 아이디
	userid varchar NOT NULL UNIQUE,
	-- 패스워드
	password varchar NOT NULL,
	-- 이메일
	user_email varchar NOT NULL,
	-- 이름
	user_name varchar NOT NULL,
	-- 사용자구분
	user_division char(2) NOT NULL,
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 수업과정
CREATE TABLE works
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 수업_아이디
	course_id int NOT NULL,
	-- 과정명
	work_name varchar,
	-- 과정설명
	work_desc varchar,
	-- 과정순서
	work_seq numeric,
	-- 과정팀유형
	work_team_division char(1),
	-- 피노지오레이어아이디
	pinogio_layer_id varchar(128),
	PRIMARY KEY (idx)
) WITHOUT OIDS;


-- 과정결과물
CREATE TABLE works_output
(
	-- 고유키
	idx serial NOT NULL UNIQUE,
	-- 수업과정아이디
	work_id int NOT NULL,
	-- 팀아이디
	output_team_id varchar,
	-- 사용자아이디
	output_userid varchar,
	-- 결과물타입
	output_division char(1),
	-- 피노지오결과물아이디
	pinogio_output_id varchar(128),
	PRIMARY KEY (idx)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE modules_work_comp
	ADD FOREIGN KEY (module_work_id)
	REFERENCES comps (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE works
	ADD FOREIGN KEY (course_id)
	REFERENCES courses (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE courses
	ADD FOREIGN KEY (module_id)
	REFERENCES modules (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE modules_work
	ADD FOREIGN KEY (module_id)
	REFERENCES modules (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE modules_work_output
	ADD FOREIGN KEY (module_id)
	REFERENCES modules (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE modules_work_comp
	ADD FOREIGN KEY (comps_id)
	REFERENCES modules_work (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE members
	ADD FOREIGN KEY (team_id)
	REFERENCES teams (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE members
	ADD FOREIGN KEY (user_id)
	REFERENCES users (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE teams
	ADD FOREIGN KEY (work_id)
	REFERENCES works (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE works_output
	ADD FOREIGN KEY (work_id)
	REFERENCES works (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

COMMENT ON TABLE authkeys IS '팀인증키';
COMMENT ON COLUMN authkeys.idx IS '고유키';
COMMENT ON COLUMN authkeys.aythkey IS '인증키';
COMMENT ON TABLE bbs_faq IS 'FAQ';
COMMENT ON COLUMN bbs_faq.idx IS '고유키';
COMMENT ON TABLE bbs_pds IS '자료실';
COMMENT ON COLUMN bbs_pds.idx IS '고유키';
COMMENT ON TABLE bbs_qna IS 'Q&A';
COMMENT ON COLUMN bbs_qna.idx IS '고유키';
COMMENT ON TABLE codes IS '코드테이블';
COMMENT ON COLUMN codes.idx IS '고유키';
COMMENT ON COLUMN codes.division IS '구분';
COMMENT ON COLUMN codes.dep1 IS '대분류';
COMMENT ON COLUMN codes.dep2 IS '중분류';
COMMENT ON COLUMN codes.dep3 IS '소분류';
COMMENT ON TABLE comps IS '공통검포넌트';
COMMENT ON COLUMN comps.idx IS '고유키';
COMMENT ON COLUMN comps.comp_name IS '컴포넌트명';
COMMENT ON COLUMN comps.comp_metadata IS '메타데이터';
COMMENT ON COLUMN comps.comp_thumbnail IS '썸네일';
COMMENT ON TABLE courses IS '수업';
COMMENT ON COLUMN courses.idx IS '고유키';
COMMENT ON COLUMN courses.module_id IS '모듈_아이디';
COMMENT ON COLUMN courses.course_name IS '수업명';
COMMENT ON COLUMN courses.course_desc IS '수업설명';
COMMENT ON COLUMN courses.course_create_id IS '생성자';
COMMENT ON COLUMN courses.course_create_date IS '생성일';
COMMENT ON TABLE members IS '팀원';
COMMENT ON COLUMN members.idx IS '고유키';
COMMENT ON COLUMN members.team_status IS '팀상태';
COMMENT ON COLUMN members.team_id IS '팀_아이디';
COMMENT ON COLUMN members.user_id IS '사용자_아이디';
COMMENT ON TABLE modules IS '모듈';
COMMENT ON COLUMN modules.idx IS '고유키';
COMMENT ON COLUMN modules.module_name IS '모듈명';
COMMENT ON COLUMN modules.module_create_date IS '생성일';
COMMENT ON COLUMN modules.module_modify_date IS '수정일';
COMMENT ON COLUMN modules.module_metadata IS '메타데이터';
COMMENT ON COLUMN modules.module_data IS '데이터';
COMMENT ON TABLE modules_work IS '모듈과정';
COMMENT ON COLUMN modules_work.idx IS '고유키';
COMMENT ON COLUMN modules_work.module_id IS '모듈아이디';
COMMENT ON COLUMN modules_work.module_work_name IS '과정명';
COMMENT ON COLUMN modules_work.module_work_metadata IS '과정메타데이터';
COMMENT ON TABLE modules_work_comp IS '모듈과정컴포넌트';
COMMENT ON COLUMN modules_work_comp.idx IS '고유키';
COMMENT ON COLUMN modules_work_comp.module_work_id IS '모듈과정_아이디';
COMMENT ON COLUMN modules_work_comp.comps_id IS '공통컴포넌트_아이디';
COMMENT ON TABLE modules_work_output IS '모듈과정자료';
COMMENT ON COLUMN modules_work_output.idx IS '고유키';
COMMENT ON COLUMN modules_work_output.module_id IS '모듈_아이디';
COMMENT ON COLUMN modules_work_output.module_work_output_name IS '자료명';
COMMENT ON COLUMN modules_work_output.module_work_output_path IS '자료위치경로';
COMMENT ON COLUMN modules_work_output.module_work_output_division IS '수업데이터구분';
COMMENT ON COLUMN modules_work_output.module_work_seq IS '수업차수정보';
COMMENT ON TABLE schools IS '학교';
COMMENT ON COLUMN schools.idx IS '고유키';
COMMENT ON COLUMN schools.school_id IS '학교아이디';
COMMENT ON COLUMN schools.school_name IS '학교명';
COMMENT ON TABLE teams IS '팀';
COMMENT ON COLUMN teams.idx IS '고유키';
COMMENT ON COLUMN teams.work_id IS '수업과정_아이디';
COMMENT ON COLUMN teams.authkey IS '인증키';
COMMENT ON TABLE users IS '사용자';
COMMENT ON COLUMN users.idx IS '고유키';
COMMENT ON COLUMN users.userid IS '아이디';
COMMENT ON COLUMN users.password IS '패스워드';
COMMENT ON COLUMN users.user_email IS '이메일';
COMMENT ON COLUMN users.user_name IS '이름';
COMMENT ON COLUMN users.user_division IS '사용자구분';
COMMENT ON TABLE works IS '수업과정';
COMMENT ON COLUMN works.idx IS '고유키';
COMMENT ON COLUMN works.course_id IS '수업_아이디';
COMMENT ON COLUMN works.work_name IS '과정명';
COMMENT ON COLUMN works.work_desc IS '과정설명';
COMMENT ON COLUMN works.work_seq IS '과정순서';
COMMENT ON COLUMN works.work_team_division IS '과정팀유형';
COMMENT ON COLUMN works.pinogio_layer_id IS '피노지오레이어아이디';
COMMENT ON TABLE works_output IS '과정결과물';
COMMENT ON COLUMN works_output.idx IS '고유키';
COMMENT ON COLUMN works_output.work_id IS '수업과정아이디';
COMMENT ON COLUMN works_output.output_team_id IS '팀아이디';
COMMENT ON COLUMN works_output.output_userid IS '사용자아이디';
COMMENT ON COLUMN works_output.output_division IS '결과물타입';
COMMENT ON COLUMN works_output.pinogio_output_id IS '피노지오결과물아이디';



