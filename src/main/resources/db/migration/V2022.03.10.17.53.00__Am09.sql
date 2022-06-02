CREATE TABLE public.license
(
    license_id serial NOT NULL,
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    PRIMARY KEY (license_id)
);

ALTER TABLE IF EXISTS public.license
    OWNER to postgre;