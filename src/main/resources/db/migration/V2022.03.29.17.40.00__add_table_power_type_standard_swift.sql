CREATE TABLE public.power_type_standard_swift
(
    id serial NOT NULL,
    standard_swift_id integer NOT NULL,
    power_type_id integer NOT NULL,
    created_by character varying,
    created_date time without time zone,
    last_modified_by character varying,
    last_modified_date time without time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.power_type_standard_swift
    OWNER to postgre;

ALTER TABLE IF EXISTS public.power_type_standard_swift
    ADD FOREIGN KEY (standard_swift_id)
    REFERENCES public.standard_swift (standard_swift_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.power_type_standard_swift
    ADD FOREIGN KEY (power_type_id)
    REFERENCES public.power_type (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;