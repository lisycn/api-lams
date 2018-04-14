
ALTER TABLE `lams`.`applications` 
ADD COLUMN `is_loan_details_complete` BIT(1) NULL;


ALTER TABLE `lams`.`applications` 
ADD COLUMN `is_loan_details_lock` BIT(1) NULL;
