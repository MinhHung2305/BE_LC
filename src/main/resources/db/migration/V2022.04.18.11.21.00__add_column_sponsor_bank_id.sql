ALTER TABLE public.financing_limit ADD sponsor_bank_id bigint NOT NULL DEFAULT 1;
COMMENT ON COLUMN public.financing_limit.sponsor_bank_id IS 'Ngan hang tai tro';
