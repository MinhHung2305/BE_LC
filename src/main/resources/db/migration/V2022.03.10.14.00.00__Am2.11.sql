CREATE TABLE public.lc_classify
(
    lc_id serial NOT NULL,
    lc_name character varying(30) NOT NULL,
    lc_type character varying(30) NOT NULL,
    lc_status character varying(10),
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "PK_lc_id" PRIMARY KEY (lc_id)
);

ALTER TABLE IF EXISTS public.lc_classify
    OWNER to postgre;

