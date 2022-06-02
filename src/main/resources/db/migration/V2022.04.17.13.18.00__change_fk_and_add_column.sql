--Change foreign key for user_sign

ALTER TABLE public.contract DROP CONSTRAINT IF EXISTS  "FK_buyer_digital_signature";

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_buyer_digital_signature" FOREIGN KEY (buyer_digital_signature)
    REFERENCES public.user_info (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE public.contract DROP CONSTRAINT IF EXISTS  "FK_seller_verifier";

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_seller_verifier" FOREIGN KEY (seller_verifier)
    REFERENCES public.user_info (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE public.contract DROP CONSTRAINT IF EXISTS  "FK_seller_digital_signature";

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT "FK_seller_digital_signature" FOREIGN KEY (seller_digital_signature)
    REFERENCES public.user_info (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

-- Add column address

ALTER TABLE public.contract ADD COLUMN IF NOT EXISTS seller_address VARCHAR(500);
ALTER TABLE public.contract ADD COLUMN IF NOT EXISTS buyer_address VARCHAR(500);