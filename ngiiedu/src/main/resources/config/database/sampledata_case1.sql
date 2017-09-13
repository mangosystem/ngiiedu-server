-- 팀인증키
INSERT INTO authkeys ( aythkey) VALUES ('인증키_1');
INSERT INTO authkeys ( aythkey) VALUES ('인증키_2');
INSERT INTO authkeys ( aythkey) VALUES ('인증키_3');
INSERT INTO authkeys ( aythkey) VALUES ('인증키_4');
INSERT INTO authkeys ( aythkey) VALUES ('인증키_5');


-- 코드테이블
INSERT INTO codes ( division, dep1, dep2, dep3) VALUES ('1', '대분류1', '중분류1', '소분류1');
INSERT INTO codes ( division, dep1, dep2, dep3) VALUES ('2', '대분류2', '중분류2', '소분류2');
INSERT INTO codes ( division, dep1, dep2, dep3) VALUES ('3', '대분류3', '중분류3', '소분류3');
INSERT INTO codes ( division, dep1, dep2, dep3) VALUES ('4', '대분류4', '중분류4', '소분류4');
INSERT INTO codes ( division, dep1, dep2, dep3) VALUES ('5', '대분류5', '중분류5', '소분류5');


-- 공통검포넌트
INSERT INTO comps ( comp_name, comp_metadata, comp_thumbnail) VALUES ('컴포넌트명1', '메타데이터1', '썸네일1');
INSERT INTO comps ( comp_name, comp_metadata, comp_thumbnail) VALUES ('컴포넌트명2', '메타데이터2', '썸네일2');
INSERT INTO comps ( comp_name, comp_metadata, comp_thumbnail) VALUES ('컴포넌트명3', '메타데이터3', '썸네일3');
INSERT INTO comps ( comp_name, comp_metadata, comp_thumbnail) VALUES ('컴포넌트명4', '메타데이터4', '썸네일4');
INSERT INTO comps ( comp_name, comp_metadata, comp_thumbnail) VALUES ('컴포넌트명5', '메타데이터5', '썸네일5');

-- 모듈
INSERT INTO modules ( module_name, module_create_date, module_modify_date, module_metadata, module_data) VALUES ('모듈명1', '생성일1', '수정일1', '메타데이터1', '데이터1');
INSERT INTO modules ( module_name, module_create_date, module_modify_date, module_metadata, module_data) VALUES ('모듈명2', '생성일2', '수정일2', '메타데이터2', '데이터2');
INSERT INTO modules ( module_name, module_create_date, module_modify_date, module_metadata, module_data) VALUES ('모듈명3', '생성일3', '수정일3', '메타데이터3', '데이터3');
INSERT INTO modules ( module_name, module_create_date, module_modify_date, module_metadata, module_data) VALUES ('모듈명4', '생성일4', '수정일4', '메타데이터4', '데이터4');
INSERT INTO modules ( module_name, module_create_date, module_modify_date, module_metadata, module_data) VALUES ('모듈명5', '생성일5', '수정일5', '메타데이터5', '데이터5');


-- 수업
INSERT INTO courses ( module_id, course_name, course_desc, course_create_id, course_create_date) VALUES ('1', '수업명1', '수업설명1', '생성자1', '생성일_1');
INSERT INTO courses ( module_id, course_name, course_desc, course_create_id, course_create_date) VALUES ('2', '수업명2', '수업설명2', '생성자2', '생성일_2');
INSERT INTO courses ( module_id, course_name, course_desc, course_create_id, course_create_date) VALUES ('3', '수업명3', '수업설명3', '생성자3', '생성일_3');
INSERT INTO courses ( module_id, course_name, course_desc, course_create_id, course_create_date) VALUES ('4', '수업명4', '수업설명4', '생성자4', '생성일_4');
INSERT INTO courses ( module_id, course_name, course_desc, course_create_id, course_create_date) VALUES ('5', '수업명5', '수업설명5', '생성자5', '생성일_5');


-- 수업과정
INSERT INTO works ( course_id, work_name, work_desc, work_seq, work_team_division, pinogio_layer_id) VALUES ('1', '과정명1', '과정설명1', '1', '1', '피노지오레이어아이디_1');
INSERT INTO works ( course_id, work_name, work_desc, work_seq, work_team_division, pinogio_layer_id) VALUES ('2', '과정명2', '과정설명2', '2', '2', '피노지오레이어아이디_2');
INSERT INTO works ( course_id, work_name, work_desc, work_seq, work_team_division, pinogio_layer_id) VALUES ('3', '과정명3', '과정설명3', '3', '3', '피노지오레이어아이디_3');
INSERT INTO works ( course_id, work_name, work_desc, work_seq, work_team_division, pinogio_layer_id) VALUES ('4', '과정명4', '과정설명4', '4', '4', '피노지오레이어아이디_4');
INSERT INTO works ( course_id, work_name, work_desc, work_seq, work_team_division, pinogio_layer_id) VALUES ('5', '과정명5', '과정설명5', '5', '5', '피노지오레이어아이디_5');


-- 팀
INSERT INTO teams ( work_id, authkey) VALUES ( '1', '인증키_1');
INSERT INTO teams ( work_id, authkey) VALUES ( '2', '인증키_2');
INSERT INTO teams ( work_id, authkey) VALUES ( '3', '인증키_3');
INSERT INTO teams ( work_id, authkey) VALUES ( '4', '인증키_4');
INSERT INTO teams ( work_id, authkey) VALUES ( '5', '인증키_5');


-- 사용자
INSERT INTO users ( userid, password, user_email, user_name, user_division) VALUES ( 'user1', 'p1', '이메일1', '이름1', '1');
INSERT INTO users ( userid, password, user_email, user_name, user_division) VALUES ( 'user2', 'p2', '이메일2', '이름2', '1');
INSERT INTO users ( userid, password, user_email, user_name, user_division) VALUES ( 'user3', 'p3', '이메일1', '이름3', '1');
INSERT INTO users ( userid, password, user_email, user_name, user_division) VALUES ( 'user4', 'p4', '이메일4', '이름4', '2');
INSERT INTO users ( userid, password, user_email, user_name, user_division) VALUES ( 'user5', 'p5', '이메일5', '이름5', '5');


-- 팀원
INSERT INTO members ( team_status, team_id, user_id) VALUES ('1', '1', '1');
INSERT INTO members ( team_status, team_id, user_id) VALUES ('2', '2', '2');
INSERT INTO members ( team_status, team_id, user_id) VALUES ('3', '3', '3');
INSERT INTO members ( team_status, team_id, user_id) VALUES ('4', '4', '4');
INSERT INTO members ( team_status, team_id, user_id) VALUES ('5', '5', '5');


-- 모듈과정
INSERT INTO modules_work ( module_id, module_work_name, module_work_metadata) VALUES ('1', '과정명1', '과정메타데이터1');
INSERT INTO modules_work ( module_id, module_work_name, module_work_metadata) VALUES ('2', '과정명2', '과정메타데이터2');
INSERT INTO modules_work ( module_id, module_work_name, module_work_metadata) VALUES ('3', '과정명3', '과정메타데이터3');
INSERT INTO modules_work ( module_id, module_work_name, module_work_metadata) VALUES ('4', '과정명4', '과정메타데이터4');
INSERT INTO modules_work ( module_id, module_work_name, module_work_metadata) VALUES ('5', '과정명5', '과정메타데이터5');


-- 모듈과정컴포넌트
INSERT INTO modules_work_comp ( module_work_id, comps_id) VALUES ('1', '1');
INSERT INTO modules_work_comp ( module_work_id, comps_id) VALUES ('2', '2');
INSERT INTO modules_work_comp ( module_work_id, comps_id) VALUES ('3', '3');
INSERT INTO modules_work_comp ( module_work_id, comps_id) VALUES ('4', '4');
INSERT INTO modules_work_comp ( module_work_id, comps_id) VALUES ('5', '5');


-- 모듈과정자료
INSERT INTO modules_work_output ( module_id, module_work_output_name, module_work_output_path, module_work_output_division, module_work_seq) VALUES ('1', '자료명1', '자료위치경로1', '1', '수업차수정보1');
INSERT INTO modules_work_output ( module_id, module_work_output_name, module_work_output_path, module_work_output_division, module_work_seq) VALUES ('2', '자료명2', '자료위치경로2', '2', '수업차수정보2');
INSERT INTO modules_work_output ( module_id, module_work_output_name, module_work_output_path, module_work_output_division, module_work_seq) VALUES ('3', '자료명3', '자료위치경로3', '3', '수업차수정보3');
INSERT INTO modules_work_output ( module_id, module_work_output_name, module_work_output_path, module_work_output_division, module_work_seq) VALUES ('4', '자료명4', '자료위치경로4', '4', '수업차수정보4');
INSERT INTO modules_work_output ( module_id, module_work_output_name, module_work_output_path, module_work_output_division, module_work_seq) VALUES ('5', '자료명5', '자료위치경로5', '5', '수업차수정보5');


-- 학교
INSERT INTO schools ( school_id, school_name) VALUES ('학교아이디1', '학교명1');
INSERT INTO schools ( school_id, school_name) VALUES ('학교아이디2', '학교명2');
INSERT INTO schools ( school_id, school_name) VALUES ('학교아이디3', '학교명3');
INSERT INTO schools ( school_id, school_name) VALUES ('학교아이디4', '학교명4');
INSERT INTO schools ( school_id, school_name) VALUES ('학교아이디5', '학교명5');


-- 과정결과물
INSERT INTO works_output ( work_id, output_team_id, output_userid, output_division, pinogio_output_id) VALUES ('1', '팀아이디1', '사용자아이디1', '1', '피노지오결과물아이디_1');
INSERT INTO works_output ( work_id, output_team_id, output_userid, output_division, pinogio_output_id) VALUES ('2', '팀아이디2', '사용자아이디2', '2', '피노지오결과물아이디_2');
INSERT INTO works_output ( work_id, output_team_id, output_userid, output_division, pinogio_output_id) VALUES ('3', '팀아이디3', '사용자아이디3', '3', '피노지오결과물아이디_3');
INSERT INTO works_output ( work_id, output_team_id, output_userid, output_division, pinogio_output_id) VALUES ('4', '팀아이디4', '사용자아이디4', '4', '피노지오결과물아이디_4');
INSERT INTO works_output ( work_id, output_team_id, output_userid, output_division, pinogio_output_id) VALUES ('5', '팀아이디5', '사용자아이디5', '5', '피노지오결과물아이디_5');


