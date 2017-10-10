-- Table: public.courses_works_output

-- DROP TABLE public.courses_works_output;

CREATE TABLE public.courses_works_output
(
	idx serial NOT NULL, -- 고유키
	work_id integer NOT NULL, -- 수업과정아이디
	output_team_id character varying, -- 팀아이디
	output_userid character varying, -- 사용자아이디
	output_division character(1), -- 결과물타입
	pinogio_output_id character varying(128), -- 피노지오결과물아이디
	CONSTRAINT works_output_pkey PRIMARY KEY (idx)
);

ALTER TABLE public.courses_works_output OWNER TO ngiiedu;

COMMENT ON TABLE public.courses_works_output IS '과정결과물';

COMMENT ON COLUMN public.courses_works_output.idx IS '고유키';
COMMENT ON COLUMN public.courses_works_output.work_id IS '수업과정아이디';
COMMENT ON COLUMN public.courses_works_output.output_team_id IS '팀아이디';
COMMENT ON COLUMN public.courses_works_output.output_userid IS '사용자아이디';
COMMENT ON COLUMN public.courses_works_output.output_division IS '결과물타입';
COMMENT ON COLUMN public.courses_works_output.pinogio_output_id IS '피노지오결과물아이디';
