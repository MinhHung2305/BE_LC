ALTER TABLE IF EXISTS public.fee_rule_bank_rating
    ADD COLUMN created_by character varying,
    ADD COLUMN created_date time without time zone,
    ADD COLUMN last_modified_by character varying,
    ADD COLUMN last_modified_date time without time zone;