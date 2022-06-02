ALTER TABLE public.financing_limit ADD COLUMN IF NOT EXISTS reason_for_change_financing_limit varchar NULL;
COMMENT ON COLUMN public.financing_limit.reason_for_change_financing_limit IS 'ly do dieu chinh han muc';
