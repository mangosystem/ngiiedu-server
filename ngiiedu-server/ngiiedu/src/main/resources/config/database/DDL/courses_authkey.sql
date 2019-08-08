-- Table: public.authkeys

-- DROP TABLE public.authkeys;

CREATE TABLE public.courses_authkey
(
	idx serial NOT NULL, -- 고유키
	authkey character(6) NOT NULL, -- 인증키
	course_id integer NOT NULL, -- 수업아이디
	status boolean NOT NULL DEFAULT false, -- 상태
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	CONSTRAINT courses_authkey_pkey PRIMARY KEY (idx),
	CONSTRAINT courses_authkey_authkey_key UNIQUE (authkey)
);

ALTER TABLE public.courses_authkey OWNER TO ngiiedu;

COMMENT ON TABLE public.courses_authkey IS '수업인증키';

COMMENT ON COLUMN public.courses_authkey.idx IS '고유키';
COMMENT ON COLUMN public.courses_authkey.authkey IS '인증키';
COMMENT ON COLUMN public.courses_authkey.course_id IS '수업아이디';
COMMENT ON COLUMN public.courses_authkey.status IS '상태';
COMMENT ON COLUMN public.courses_authkey.create_date IS '생성일';
