ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS confirming_bank_id_request bigint NULL;
COMMENT ON COLUMN public.application_opening_lc.confirming_bank_id_request IS 'ngan hang yeu cau xac nhan';
ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS bank_id_presentation_at bigint NULL;
COMMENT ON COLUMN public.application_opening_lc.bank_id_presentation_at IS 'ngan hang se xuat trinh chung tu';
ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS product_type varchar NULL;
COMMENT ON COLUMN public.application_opening_lc.product_type IS 'loai hang hoa';
