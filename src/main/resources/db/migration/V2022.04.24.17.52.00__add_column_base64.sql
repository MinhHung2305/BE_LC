ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS filekyso_base64 varchar NULL;
ALTER TABLE public.application_opening_lc ADD COLUMN IF NOT EXISTS status_sign_digital int4 NULL;
COMMENT ON COLUMN public.application_opening_lc.status_sign_digital IS 'trang thai ky so';
