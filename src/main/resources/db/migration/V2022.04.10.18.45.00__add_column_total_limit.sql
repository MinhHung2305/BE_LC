ALTER TABLE public.financing_limit ADD total_limit bigint NULL;
COMMENT ON COLUMN public.financing_limit.total_limit IS 'tong han muc';
