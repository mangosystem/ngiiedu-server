-- Table: public.modules

-- DROP TABLE public.modules;

CREATE TABLE public.modules
(
	idx serial NOT NULL, -- 고유키
	module_name character varying, -- 모듈명
	module_metadata character varying, -- 메타데이터
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	modify_date timestamp with time zone DEFAULT now(), -- 수정일
	status boolean NOT NULL DEFAULT true, -- 활성화상태
	CONSTRAINT modules_pkey PRIMARY KEY (idx)
);

ALTER TABLE public.modules OWNER TO ngiiedu;

COMMENT ON TABLE public.modules IS '모듈';
COMMENT ON COLUMN public.modules.idx IS '고유키';
COMMENT ON COLUMN public.modules.module_name IS '모듈명';
COMMENT ON COLUMN public.modules.module_metadata IS '메타데이터';
COMMENT ON COLUMN public.modules.create_date IS '생성일';
COMMENT ON COLUMN public.modules.modify_date IS '수정일';
COMMENT ON COLUMN public.modules.status IS '활성화상태';
