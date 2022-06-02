ALTER TABLE public.contract ADD COLUMN IF NOT EXISTS representative_seller int4 NULL;
ALTER TABLE public.contract ADD COLUMN IF NOT EXISTS representative_buyer int4 NULL;