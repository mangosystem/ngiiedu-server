-- Table: public.courses_team_member

-- DROP TABLE public.courses_team_member;

CREATE TABLE public.courses_team_member
(
	idx serial NOT NULL, -- 고유키
	member_id integer NOT NULL, --수업참여자 아이디
	team_id integer NOT NULL, -- 팀 아이디
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	CONSTRAINT courses_team_member_pkey PRIMARY KEY (idx),
	CONSTRAINT courses_team_member_member_id_fkey FOREIGN KEY (member_id)
		REFERENCES public.courses_member (idx) MATCH SIMPLE
		ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT courses_team_member_team_id_fkey FOREIGN KEY (team_id)
		REFERENCES public.courses_team (idx) MATCH SIMPLE
		ON UPDATE RESTRICT ON DELETE RESTRICT
		
);

ALTER TABLE public.courses_team_member OWNER TO ngiiedu;

COMMENT ON TABLE public.courses_team_member IS '팀 멤버';
COMMENT ON COLUMN public.courses_team_member.idx IS '고유키';
COMMENT ON COLUMN public.courses_team_member.member_id IS '수업참여자 아이디';
COMMENT ON COLUMN public.courses_team_member.team_id IS '팀 아이디';
COMMENT ON COLUMN public.courses_team_member.create_date IS '생성일';
