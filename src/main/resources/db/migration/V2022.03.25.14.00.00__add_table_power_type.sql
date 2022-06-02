CREATE TABLE IF NOT EXISTS public.power_type
(
    id serial NOT NULL,
    power_type character varying NOT NULL,
    power_name character varying NOT NULL,
    purpose_use character varying NOT NULL,
    created_by character varying,
    created_date time without time zone,
    last_modified_by character varying,
    last_modified_date time without time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.power_type
    OWNER to postgre;

CREATE TABLE IF NOT EXISTS public.character_set
(
    id serial NOT NULL,
    character_set character varying NOT NULL,
    applicable_characters character varying NOT NULL,
    note character varying NOT NULL,
    created_by character varying,
    created_date time without time zone,
    last_modified_by character varying,
    last_modified_date character varying,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.character_set
    OWNER to postgre;