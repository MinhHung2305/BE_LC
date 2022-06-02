ALTER TABLE public.financing_limit ADD number_of_changes int4 NULL DEFAULT 0;
COMMENT ON COLUMN public.financing_limit.number_of_changes IS 'so lan thay doi';