ALTER TABLE public.financing_limit_transaction ADD created_by varchar(255) NULL;
ALTER TABLE public.financing_limit_transaction ADD created_date timestamp NULL;
ALTER TABLE public.financing_limit_transaction ADD last_modified_by varchar(255) NULL;
ALTER TABLE public.financing_limit_transaction ADD last_modified_date timestamp NULL;

ALTER TABLE public.financing_limit_change_history ADD created_by varchar(255) NULL;
ALTER TABLE public.financing_limit_change_history ADD created_date timestamp NULL;
ALTER TABLE public.financing_limit_change_history ADD last_modified_by varchar(255) NULL;
ALTER TABLE public.financing_limit_change_history ADD last_modified_date timestamp NULL;
