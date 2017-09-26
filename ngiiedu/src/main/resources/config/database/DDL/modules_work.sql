-- Table: public.modules_work

-- DROP TABLE public.modules_work;

CREATE TABLE public.modules_work
(
  idx serial NOT NULL, -- 고유키
  module_id integer NOT NULL, -- 모듈아이디
  module_work_name character varying, -- 과정명
  module_work_seq integer DEFAULT 0, -- 과정순서
  module_work_course_type character varying(128), -- 과정수업방식
  create_date timestamp with time zone DEFAULT now(), -- 생성일
  modify_date timestamp with time zone DEFAULT now(), -- 수정일
  module_work_metadata text, -- 과정메타데이터
  CONSTRAINT modules_work_pkey PRIMARY KEY (idx)
);

ALTER TABLE public.modules_work OWNER TO ngiiedu;

COMMENT ON TABLE public.modules_work IS '모듈과정';
COMMENT ON COLUMN public.modules_work.idx IS '고유키';
COMMENT ON COLUMN public.modules_work.module_id IS '모듈아이디';
COMMENT ON COLUMN public.modules_work.module_work_name IS '과정명';
COMMENT ON COLUMN public.modules_work.module_work_seq IS '과정순서';
COMMENT ON COLUMN public.modules_work.module_work_course_type IS '과정수업방식';
COMMENT ON COLUMN public.modules_work.create_date IS '생성일';
COMMENT ON COLUMN public.modules_work.modify_date IS '수정일';
COMMENT ON COLUMN public.modules_work.module_work_metadata IS '과정메타데이터';
