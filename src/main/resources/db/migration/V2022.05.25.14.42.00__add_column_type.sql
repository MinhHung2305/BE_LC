ALTER TABLE public.character_set_electric_type ADD COLUMN IF NOT EXISTS "type" int4 NULL;
COMMENT ON COLUMN public.character_set_electric_type."type" IS 'loai dien tao hay nhan';
