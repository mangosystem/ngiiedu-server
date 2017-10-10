-- Table: public.courses_work

-- DROP TABLE public.courses_work;

CREATE TABLE public.courses_work
(
	idx serial NOT NULL, -- 고유키
	course_id integer NOT NULL, -- 수업아이디
	module_work_id integer NOT NULL, --모듈과정아이디
	work_seq integer, -- 과정순서
	work_team_division character varying, -- 과정팀유형
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	modify_date timestamp with time zone DEFAULT now(), -- 수정일
	CONSTRAINT courses_work_pkey PRIMARY KEY (idx),
	CONSTRAINT courses_work_course_id_fkey FOREIGN KEY (course_id)
		REFERENCES public.courses (idx) MATCH SIMPLE
		ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE public.courses_work OWNER TO ngiiedu;

COMMENT ON TABLE public.courses_work IS '수업과정';
COMMENT ON COLUMN public.courses_work.idx IS '고유키';
COMMENT ON COLUMN public.courses_work.course_id IS '수업아이디';
COMMENT ON COLUMN public.courses_work.module_work_id IS '모듈과정아이디';
COMMENT ON COLUMN public.courses_work.work_seq IS '과정순서';
COMMENT ON COLUMN public.courses_work.work_team_division IS '과정팀유형';
COMMENT ON COLUMN public.courses_work.create_date IS '생성일';
COMMENT ON COLUMN public.courses_work.modify_date IS '수정일';
