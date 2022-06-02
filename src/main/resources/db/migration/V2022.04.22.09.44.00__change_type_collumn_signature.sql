ALTER TABLE public.corporate ALTER COLUMN signature_expiration_date TYPE varchar USING signature_expiration_date::varchar;
ALTER TABLE public.corporate ALTER COLUMN signature_value_date TYPE varchar USING signature_value_date::varchar;
