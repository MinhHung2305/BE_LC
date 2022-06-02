--add column contract_value_before_vat -- tien truoc thue
ALTER TABLE public.contract
ADD COLUMN IF NOT EXISTS contract_value_before_vat numeric NULL;

--add column license_description -- mo ta chung tu
ALTER TABLE public.contract_license
ADD COLUMN IF NOT EXISTS license_description varchar NULL;