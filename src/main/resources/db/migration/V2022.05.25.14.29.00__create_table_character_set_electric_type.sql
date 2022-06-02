CREATE TABLE public.character_set_electric_type (
                                                    id serial8 NOT NULL,
                                                    field_number varchar NULL,
                                                    field_name varchar NULL,
                                                    format varchar NULL,
                                                    max_length varchar NULL,
                                                    description text NULL,
                                                    sample text NULL,
                                                    electric_type_id bigint NULL,
                                                    CONSTRAINT character_set_electric_type_pk PRIMARY KEY (id),
                                                    CONSTRAINT character_set_electric_type_fk FOREIGN KEY (electric_type_id) REFERENCES public.electric_type(id)
);

-- Column comments

COMMENT ON COLUMN public.character_set_electric_type.field_number IS 'so truong';
COMMENT ON COLUMN public.character_set_electric_type.field_name IS 'ten truong';
COMMENT ON COLUMN public.character_set_electric_type.format IS 'dinh dang';
COMMENT ON COLUMN public.character_set_electric_type.max_length IS 'do dai toi da';
COMMENT ON COLUMN public.character_set_electric_type.description IS 'mo ta';
COMMENT ON COLUMN public.character_set_electric_type.sample IS 'vi du minh hoa';
