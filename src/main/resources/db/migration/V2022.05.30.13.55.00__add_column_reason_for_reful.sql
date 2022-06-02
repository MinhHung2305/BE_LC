ALTER TABLE public.financing_limit ADD COLUMN IF NOT EXISTS reason_for_refusal text NULL;
COMMENT ON COLUMN public.financing_limit.reason_for_refusal IS 'ly do tu choi';
