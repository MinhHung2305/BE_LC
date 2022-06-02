update corporate_account set bank_id = (select bank_id from bank_info where bank_code = 'BFTV') where bank_id in (select bank_id from bank_info where bank_code in ('VCB', 'TCB'));
update user_group ug set bank_id = (select bank_id from bank_info where bank_code = 'BFTV') where bank_id in (select bank_id from bank_info where bank_code in ('VCB', 'TCB'));
update financing_limit ug set bank_id = (select bank_id from bank_info where bank_code = 'BFTV') where bank_id in (select bank_id from bank_info where bank_code in ('VCB', 'TCB'));
update application_opening_lc set bank_id = (select bank_id from bank_info where bank_code = 'BFTV') where bank_id in (select bank_id from bank_info where bank_code in ('VCB', 'TCB'));
update financing_limit set bank_id = (select bank_id from bank_info where bank_code = 'BFTV') where bank_id in (select bank_id from bank_info where bank_code in ('VCB', 'TCB'));
delete from bank_info where bank_code in('VCB', 'TCB');