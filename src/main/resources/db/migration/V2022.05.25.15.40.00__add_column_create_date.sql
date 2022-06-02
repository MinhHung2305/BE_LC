ALTER TABLE public.character_set_electric_type ADD created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE public.character_set_electric_type ADD created_by varchar NULL;
ALTER TABLE public.character_set_electric_type ADD last_modified_by varchar NULL;
ALTER TABLE public.character_set_electric_type ADD last_modified_date timestamp NULL DEFAULT CURRENT_TIMESTAMP;
