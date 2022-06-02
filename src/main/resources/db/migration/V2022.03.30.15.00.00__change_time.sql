ALTER TABLE public.power_type_standard_swift DROP COLUMN created_date;
ALTER TABLE public.power_type_standard_swift DROP COLUMN last_modified_date;
ALTER TABLE public.power_type_standard_swift ADD created_date timestamp NULL;
ALTER TABLE public.power_type_standard_swift ADD last_modified_date timestamp NULL;

ALTER TABLE public.character_set DROP COLUMN created_date;
ALTER TABLE public.character_set DROP COLUMN last_modified_date;
ALTER TABLE public.character_set ADD created_date timestamp NULL;
ALTER TABLE public.character_set ADD last_modified_date timestamp NULL;

ALTER TABLE public.power_type DROP COLUMN created_date;
ALTER TABLE public.power_type DROP COLUMN last_modified_date;
ALTER TABLE public.power_type ADD created_date timestamp NULL;
ALTER TABLE public.power_type ADD last_modified_date timestamp NULL;

ALTER TABLE public.standard_swift DROP COLUMN created_date;
ALTER TABLE public.standard_swift DROP COLUMN last_modified_date;
ALTER TABLE public.standard_swift ADD created_date timestamp NULL;
ALTER TABLE public.standard_swift ADD last_modified_date timestamp NULL;