CREATE TABLE public.product_type (
                                     id serial4 NOT NULL,
                                     product_code varchar(200) NOT NULL,
                                     product_name varchar(100) NOT NULL,
                                     created_date timestamptz NULL,
                                     created_by varchar NULL,
                                     last_modified_by varchar NULL,
                                     last_modified_date timestamptz NULL,
                                     parent bigint NULL,
                                     status int4 NULL,
                                     CONSTRAINT product_type_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.product_type.parent IS 'Ma hang hoa cap cha';
COMMENT ON COLUMN public.product_type.product_code IS 'Ma hang hoa';
COMMENT ON COLUMN public.product_type.product_name IS 'Ten hang hoa';

ALTER TABLE IF EXISTS public.product_type
    OWNER to postgre;
