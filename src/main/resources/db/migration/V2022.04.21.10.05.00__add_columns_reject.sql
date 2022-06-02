-- add column reasons_for_refusing_the_buyer
ALTER TABLE public.contract
 ADD COLUMN IF NOT EXISTS reasons_for_refusing_the_buyer varchar NULL;
