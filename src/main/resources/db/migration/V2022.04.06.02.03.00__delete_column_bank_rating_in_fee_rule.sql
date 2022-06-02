ALTER TABLE IF EXISTS public.fee_rules DROP COLUMN IF EXISTS bank_rating_id;
ALTER TABLE IF EXISTS public.fee_rules DROP CONSTRAINT IF EXISTS fee_rules_bank_rating_id_fkey;