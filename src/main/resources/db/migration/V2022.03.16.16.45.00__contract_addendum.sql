ALTER TABLE IF EXISTS public.contract DROP COLUMN IF EXISTS appendix_no;

ALTER TABLE IF EXISTS public.contract DROP COLUMN IF EXISTS appendix_name;

ALTER TABLE IF EXISTS public.contract DROP COLUMN IF EXISTS appendix_content;

CREATE TABLE public.contract_addendum
(
    addendum_no serial NOT NULL,
    addendum_name character varying(100),
    addendum_content character varying(5000),
    PRIMARY KEY (addendum_no)
);

ALTER TABLE IF EXISTS public.contract_addendum
    OWNER to postgre;
