ALTER TABLE `lams`.`lender_borrower_connection` 
ADD COLUMN `status` VARCHAR(45) NULL AFTER `lender_application_mapping_id`;

INSERT INTO `lams`.`mstr_base` (`id`, `name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES ('28', 'Ms.', 'Ms.', 1, 1, NOW(), 1, NOW());
INSERT INTO `lams`.`salutation_mstr` (`id`) VALUES ('28');
INSERT INTO `lams`.`mstr_base` (`id`, `name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES ('29', 'Mrs.', 'Mrs.', 1, 1, NOW(), 1, NOW());
INSERT INTO `lams`.`salutation_mstr` (`id`) VALUES ('29');

ALTER TABLE `lams`.`applications` 
ADD COLUMN `is_upload_complete` BIT(1) NULL AFTER `status`;


