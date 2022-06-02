--Change type contract_value fix bug integer out of range

ALTER TABLE public.contract  ALTER COLUMN contract_value TYPE numeric USING contract_value::numeric;