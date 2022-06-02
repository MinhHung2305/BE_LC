ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN power_code_fpt character varying;

ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN sender_reference_number character varying;

ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN recipient_reference_number character varying;

ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN "sending_bank" integer;

ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN "receiving_bank" integer;

ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN "reason_for_refusal" character varying;

ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN information character varying;