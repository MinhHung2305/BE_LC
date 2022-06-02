--Change type sign_date : timetz to timestamp

ALTER TABLE public.contract DROP COLUMN buyer_digital_signing_date;
ALTER TABLE public.contract ADD buyer_digital_signing_date timestamp NULL;
	
ALTER TABLE public.contract DROP COLUMN seller_confirmation_date;
ALTER TABLE public.contract ADD seller_confirmation_date timestamp NULL;
	
ALTER TABLE public.contract DROP COLUMN seller_digital_signing_date;
ALTER TABLE public.contract ADD seller_digital_signing_date timestamp NULL;