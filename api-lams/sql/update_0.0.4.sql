CREATE TABLE `lams`.`lender_borrower_connection` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '	',
  `loan_possible_amount` DOUBLE UNSIGNED NULL,
  `tenure` INT UNSIGNED NULL,
  `roi` DOUBLE UNSIGNED NULL,
  `processing_fees` DOUBLE UNSIGNED NULL,
  `term_and_condition` LONGTEXT NULL,
  `comments` LONGTEXT NULL,
  `created_by` BIGINT(20) UNSIGNED NULL,
  `created_date` DATETIME NULL DEFAULT NOW(),
  `modified_by` BIGINT(20) UNSIGNED NULL,
  `modified_date` DATETIME NULL,
  `is_active` BIT NULL DEFAULT true,
  PRIMARY KEY (`id`));

  
  ALTER TABLE `lams`.`applications` 
CHANGE COLUMN `id` `id` BIGINT(45) UNSIGNED NOT NULL AUTO_INCREMENT ;
ALTER TABLE `lams`.`applications` 
CHANGE COLUMN `id` `id` BIGINT(20) UNSIGNED NOT NULL ;

ALTER TABLE `lams`.`applications` 
CHANGE COLUMN `id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ;

ALTER TABLE `lams`.`user` 
CHANGE COLUMN `id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ;


ALTER TABLE `lams`.`lender_borrower_connection` 
ADD COLUMN `application_id` BIGINT(20) UNSIGNED NULL AFTER `is_active`;

ALTER TABLE `lams`.`lender_borrower_connection` 
ADD CONSTRAINT `fk_lender_borrower_connection_1`
  FOREIGN KEY (`application_id`)
  REFERENCES `lams`.`applications` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `lams`.`lender_borrower_connection` 
ADD INDEX `fk_lender_borrower_connection_2_idx` (`lender_application_mapping_id` ASC);
ALTER TABLE `lams`.`lender_borrower_connection` 
ADD CONSTRAINT `fk_lender_borrower_connection_2`
  FOREIGN KEY (`lender_application_mapping_id`)
  REFERENCES `lams`.`lender_product_mapping` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


  ALTER TABLE `lams`.`lender_borrower_connection` 
ADD COLUMN `lender_application_mapping_id` BIGINT(20) UNSIGNED NULL AFTER `application_id`;
