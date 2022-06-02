CREATE TABLE public.standard_swift
(
    standard_swift_id serial NOT NULL,
    standard_swift_no character varying NOT NULL,
    standard_swift_name character varying NOT NULL,
    mandatory integer NOT NULL,
    format character varying NOT NULL,
    max_length integer NOT NULL,
    description character varying,
    example character varying,
    created_by character varying,
    created_date time without time zone,
    last_modified_by character varying,
    last_modified_date time without time zone,
    PRIMARY KEY (standard_swift_id)
);

ALTER TABLE IF EXISTS public.standard_swift
    OWNER to postgre;