CREATE TABLE public.electric_type (
                                      id serial4 NOT NULL,
                                      electric_type varchar(255) NOT NULL,
                                      electric_name varchar(255) NOT NULL,
                                      proposal text NULL,
                                      created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      created_by varchar NULL,
                                      last_modified_by varchar NULL,
                                      last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      CONSTRAINT electric_type_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.electric_type.electric_type IS 'loai dien';
COMMENT ON COLUMN public.electric_type.electric_name IS 'ten loai dien';
COMMENT ON COLUMN public.electric_type.proposal IS 'muc dich su dung';
