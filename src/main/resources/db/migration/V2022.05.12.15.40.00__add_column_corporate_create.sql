ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS corporate_create bigint NULL;
COMMENT ON COLUMN public.application_opening_lc.corporate_create IS 'Doanh nghiep cua user tao de nghi';
