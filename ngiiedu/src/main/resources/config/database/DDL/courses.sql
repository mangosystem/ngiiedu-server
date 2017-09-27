-- Table: public.courses

-- DROP TABLE public.courses;

CREATE TABLE public.courses
(
	idx serial NOT NULL, -- 고유키
	module_id integer NOT NULL, -- 모듈_아이디
	course_name character varying, -- 수업명
	course_metadata text, -- 수업메타데이터
	course_create_id character varying, -- 생성자명
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	modify_date timestamp with time zone DEFAULT now(), -- 수정일
	CONSTRAINT courses_pkey PRIMARY KEY (idx)
);

ALTER TABLE public.courses OWNER TO ngiiedu;

COMMENT ON TABLE public.courses IS '수업';
COMMENT ON COLUMN public.courses.idx IS '고유키';
COMMENT ON COLUMN public.courses.module_id IS '모듈_아이디';
COMMENT ON COLUMN public.courses.course_name IS '수업명';
COMMENT ON COLUMN public.courses.course_metadata IS '수업메타데이터';
COMMENT ON COLUMN public.courses.course_create_id IS '생성자명';
COMMENT ON COLUMN public.courses.create_date IS '생성일';
COMMENT ON COLUMN public.courses.modify_date IS '수정일';