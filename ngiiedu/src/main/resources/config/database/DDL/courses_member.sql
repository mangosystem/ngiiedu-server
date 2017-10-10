-- Table: public.courses_member

-- DROP TABLE public.courses_member;

CREATE TABLE public.courses_member
(
	idx serial NOT NULL, -- 고유키
	course_id integer NOT NULL, -- 수업아아디
	user_id integer NOT NULL, -- 참여자아이디
	status character varying(5), -- 참여자상태,
	create_date timestamp with time zone DEFAULT now(), -- 생성일
	modify_date timestamp with time zone DEFAULT now(), -- 수정일
	CONSTRAINT courses_member_pkey PRIMARY KEY (idx),
	CONSTRAINT courses_member_user_id_fkey FOREIGN KEY (user_id)
		REFERENCES public.users (idx) MATCH SIMPLE
		ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE public.courses_member OWNER TO ngiiedu;

COMMENT ON TABLE public.courses_member IS '수업참여자';
COMMENT ON COLUMN public.courses_member.idx IS '고유키';
COMMENT ON COLUMN public.courses_member.course_id IS '수업아아디';
COMMENT ON COLUMN public.courses_member.user_id IS '참여자아이디';
COMMENT ON COLUMN public.courses_member.status IS '참여자상태';
COMMENT ON COLUMN public.courses_member.create_date IS '생성일';
COMMENT ON COLUMN public.courses_member.modify_date IS '수정일';
