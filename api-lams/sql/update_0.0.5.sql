ALTER TABLE `lams`.`lender_borrower_connection` 
ADD COLUMN `status` VARCHAR(45) NULL AFTER `lender_application_mapping_id`;

INSERT INTO `lams`.`mstr_base` (`id`, `name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES ('28', 'Ms.', 'Ms.', 1, 1, NOW(), 1, NOW());
INSERT INTO `lams`.`salutation_mstr` (`id`) VALUES ('28');
INSERT INTO `lams`.`mstr_base` (`id`, `name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES ('29', 'Mrs.', 'Mrs.', 1, 1, NOW(), 1, NOW());
INSERT INTO `lams`.`salutation_mstr` (`id`) VALUES ('29');

ALTER TABLE `lams`.`applications` 
ADD COLUMN `is_upload_complete` BIT(1) NULL AFTER `status`;


CREATE TABLE `lams`.`business_type_mstr` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_business_type_mstr_1`
    FOREIGN KEY (`id`)
    REFERENCES `lams`.`mstr_base` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
ALTER TABLE `lams`.`user` 
ADD COLUMN `contact_person_name` VARCHAR(45) NULL AFTER `is_same_us_address`,
ADD COLUMN `gst_number` VARCHAR(45) NULL AFTER `contact_person_name`,
ADD COLUMN `business_type_id` BIGINT(20) UNSIGNED NULL AFTER `gst_number`,
ADD COLUMN `channel_partner_id` BIGINT(20) UNSIGNED NULL AFTER `business_type_id`,
ADD COLUMN `about_me` LONGTEXT NULL AFTER `channel_partner_id`,
ADD INDEX `fk_user_1_idx` (`business_type_id` ASC),
ADD INDEX `fk_user_2_idx` (`channel_partner_id` ASC);
ALTER TABLE `lams`.`user` 
ADD CONSTRAINT `fk_user_1`
  FOREIGN KEY (`business_type_id`)
  REFERENCES `lams`.`business_type_mstr` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_2`
  FOREIGN KEY (`channel_partner_id`)
  REFERENCES `lams`.`user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
ALTER TABLE `lams`.`user` 
ADD COLUMN `code` VARCHAR(45) NULL AFTER `about_me`;
