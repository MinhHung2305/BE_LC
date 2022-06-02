ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS  total_value_product bigint NULL DEFAULT 0;
COMMENT ON COLUMN public.application_opening_lc.total_value_product IS 'tong gia tri hang hoa';
ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS  vat_product int4 DEFAULT 0;
COMMENT ON COLUMN public.application_opening_lc.vat_product IS 'vat hang hoa';
ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS  total_value_after_vat bigint DEFAULT 0;
