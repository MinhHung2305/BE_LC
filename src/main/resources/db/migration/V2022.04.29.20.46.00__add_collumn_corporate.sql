-- add column path img of identity and birthday

ALTER TABLE public.user_info
ADD COLUMN IF NOT EXISTS image_front_of_identity varchar(255) NULL;

ALTER TABLE public.user_info
ADD COLUMN IF NOT EXISTS image_back_of_identity varchar(255) NULL;

ALTER TABLE public.user_info
ADD COLUMN IF NOT EXISTS image_portrait_of_identity varchar(255) NULL;

ALTER TABLE public.user_info
ADD COLUMN IF NOT EXISTS date_of_birth timestamp NULL;