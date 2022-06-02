ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_rule DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_value DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_min_value DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_max_value DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_rate DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_vat DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_id DROP NOT NULL;
ALTER TABLE public.fee_rules ALTER COLUMN fee_rules_method DROP NOT NULL;