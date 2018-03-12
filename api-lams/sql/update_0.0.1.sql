ALTER TABLE `lams`.`user` 
ADD COLUMN `invitation_count` INT(3) UNSIGNED NOT NULL DEFAULT 0 AFTER `contact_number`;
ALTER TABLE `lams`.`notification_template` 
ADD UNIQUE INDEX `alias_UNIQUE` (`alias` ASC),
ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC);
INSERT INTO `lams`.`notification_template` (`created_by`, `created_date`, `is_active`, `modified_by`, `alias`, `name`, `notification_type`) VALUES (1, NOW(), 1, 1,  'EMAIL_LENDER_INVITATION', 'email_lender_invitation.ftl', 'EMAIL');
ALTER TABLE `lams`.`user` 
ADD COLUMN `temp_password` VARCHAR(100) NOT NULL AFTER `invitation_count`;
