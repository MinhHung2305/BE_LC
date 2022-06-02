ALTER TABLE IF EXISTS public.contract
    ADD COLUMN appendix_no serial;

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN appendix_name varchar(100);

ALTER TABLE IF EXISTS public.contract
    ADD COLUMN appendix_content varchar(5000);