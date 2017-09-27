-- Table: public.works

-- DROP TABLE public.works;

CREATE TABLE public.works
(
	idx serial NOT NULL, -- 고유키
	course_id integer NOT NULL, -- 수업아이디
	module_work_id integer NOT NULL, --모듈과정아이디
	work_seq integer, -- 과정순서
	work_team_division character varying, -- 과정팀유형
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	modify_date timestamp with time zone DEFAULT now(), -- 수정일
	CONSTRAINT works_pkey PRIMARY KEY (idx),
	CONSTRAINT works_course_id_fkey FOREIGN KEY (course_id)
	REFERENCES public.courses (idx) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE public.works OWNER TO ngiiedu;

COMMENT ON TABLE public.works IS '수업과정';
COMMENT ON COLUMN public.works.idx IS '고유키';
COMMENT ON COLUMN public.works.course_id IS '수업아이디';
COMMENT ON COLUMN public.works.module_work_id IS '모듈과정아이디';
COMMENT ON COLUMN public.works.work_seq IS '과정순서';
COMMENT ON COLUMN public.works.work_team_division IS '과정팀유형';
COMMENT ON COLUMN public.works.create_date IS '생성일';
COMMENT ON COLUMN public.works.modify_date IS '수정일';
