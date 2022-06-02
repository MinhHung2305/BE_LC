ALTER TABLE public.application_opening_lc DROP COLUMN delivery_time;
ALTER TABLE public.application_opening_lc ADD delivery_time varchar(255) NULL;