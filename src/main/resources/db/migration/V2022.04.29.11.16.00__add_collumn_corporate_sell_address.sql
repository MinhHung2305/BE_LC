ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS  corporate_sell_address varchar NULL;
COMMENT ON COLUMN public.application_opening_lc.corporate_sell_address IS 'dia chi ben ban';
ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS  corporate_buy_address varchar NULL;
COMMENT ON COLUMN public.application_opening_lc.corporate_buy_address IS 'dia chi ben mua';
