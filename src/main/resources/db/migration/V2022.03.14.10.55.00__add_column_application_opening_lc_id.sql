ALTER TABLE public.contract_license DROP CONSTRAINT contract_license_contract_id_fkey;
ALTER TABLE public.contract_license ADD application_opeing_lc_id int4 NULL;

