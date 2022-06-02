ALTER TABLE public.user_group ADD COLUMN IF NOT EXISTS bank_id bigint;
ALTER TABLE public.user_group ADD CONSTRAINT user_group_fk FOREIGN KEY (bank_id) REFERENCES public.bank_info(bank_id) ON DELETE CASCADE ON UPDATE CASCADE;
