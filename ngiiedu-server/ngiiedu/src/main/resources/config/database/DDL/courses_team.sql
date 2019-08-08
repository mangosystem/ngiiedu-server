-- Table: public.courses_team

-- DROP TABLE public.courses_team;

CREATE TABLE public.courses_team
(
	idx serial NOT NULL, -- 고유키
	course_id integer NOT NULL, -- 수업 아이디
	team_name character varying(128), -- 팀명
	team_seq integer DEFAULT 0, -- 팀순서
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	modify_date timestamp with time zone DEFAULT now(), -- 수정일
	CONSTRAINT courses_team_pkey PRIMARY KEY (idx),
	CONSTRAINT courses_team_course_id_fkey FOREIGN KEY (course_id)
		REFERENCES public.courses (idx) MATCH SIMPLE
		ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE public.courses_team OWNER TO ngiiedu;

COMMENT ON TABLE public.courses_team IS '팀';
COMMENT ON COLUMN public.courses_team.idx IS '고유키';
COMMENT ON COLUMN public.courses_team.course_id IS '수업 아이디';
COMMENT ON COLUMN public.courses_team.team_name IS '팀명';
COMMENT ON COLUMN public.courses_team.team_seq IS '팀순서';
COMMENT ON COLUMN public.courses_team.create_date IS '생성일';
COMMENT ON COLUMN public.courses_team.modify_date IS '수정일';
