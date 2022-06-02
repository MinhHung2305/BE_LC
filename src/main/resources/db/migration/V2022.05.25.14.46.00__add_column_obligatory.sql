ALTER TABLE public.character_set_electric_type ADD COLUMN IF NOT EXISTS obligatory boolean NULL;
COMMENT ON COLUMN public.character_set_electric_type.obligatory IS 'bat buoc hay khong';
