ALTER TABLE IF EXISTS public.full_progression_lc
    ALTER COLUMN full_progression_lc_code DROP DEFAULT;

ALTER TABLE IF EXISTS public.full_progression_lc
    ALTER COLUMN full_progression_lc_code DROP NOT NULL;