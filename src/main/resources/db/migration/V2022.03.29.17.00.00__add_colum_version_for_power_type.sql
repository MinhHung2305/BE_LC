ALTER TABLE IF EXISTS public.power_type
    ADD COLUMN version character varying NOT NULL DEFAULT('SWIFT Release November 2021');