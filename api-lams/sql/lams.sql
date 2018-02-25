create database lams;


CREATE TABLE `lams`.`address_mstr` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `street_name` VARCHAR(45) NULL,
  `land_mark` VARCHAR(45) NULL,
  `pincode` DOUBLE NULL,
  `country_id` BIGINT(20) NULL,
  `state_id` BIGINT(20) NULL,
  `city_id` BIGINT(20) NULL,
  `created_by` BIGINT(20) NULL,
  `created_date` DATETIME NULL,
  `modified_by` BIGINT(20) NULL,
  `modified_date` DATETIME NULL,
  `is_active` BIT(1) NULL,
  PRIMARY KEY (`id`));



CREATE TABLE `lams`.`bank_mstr` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `created_by` BIGINT(20) NULL,
  `created_date` DATETIME NULL,
  `modified_by` BIGINT(20) NULL,
  `modified_date` DATETIME NULL,
  `is_active` BIT(1) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `lams`.`login_audit_trail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `token` VARCHAR(45) NULL,
  `user_id` BIGINT(20) NULL,
  `user_type` BIGINT(20) NULL,
  `login_date` DATETIME	NULL,
  `is_active` BIT(1) NULL,
  PRIMARY KEY (`id`));



CREATE TABLE `lams`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `mobile` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `is_accept_term_condition` BIT(1) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `middle_name` VARCHAR(45) NULL,
  `address` BIGINT(20) NULL,
  `birth_date` DATETIME NULL,
  `gender` BIGINT(20) NULL,
  `is_otp_verified` BIT(1) NULL,
  `is_email_verified` BIT(1) NULL,
  `user_type` BIGINT(20) NULL,
  `bank` BIGINT(20) NULL,
  `created_by` BIGINT(20) NULL,
  `created_date` DATETIME NULL,
  `modified_by` BIGINT(20) NULL,
  `modified_date` DATETIME NULL,
  `is_active` BIT(1) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_mstr_address`
    FOREIGN KEY (`address`)
    REFERENCES `lams`.`address_mstr` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `fk_user_mstr_bank`
    FOREIGN KEY (`bank`)
    REFERENCES `lams`.`bank_mstr` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)



--Akshay

INSERT INTO `lams`.`user` (`name`, `email`, `mobile`, `password`,  `is_active`) VALUES ('admin', 'admin@gmail.com', '8975849586', 'e8e99ce7fa9c15f522e21e2fb2cc752c', 1);

--Master Base Table

  CREATE TABLE `lams`.`mstr_base` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(45) NULL,
  `is_active` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`));
  
ALTER TABLE `lams`.`mstr_base` ADD COLUMN `created_by` BIGINT(20) UNSIGNED NULL AFTER `is_active`,
ADD COLUMN `created_date` DATETIME NOT NULL DEFAULT NOW() AFTER `created_by`,
ADD COLUMN `modified_by` BIGINT(20) UNSIGNED NULL AFTER `created_date`,
ADD COLUMN `modified_date` DATETIME NULL AFTER `modified_by`, RENAME TO  `lams`.`mstr_base` ;



--For Country
CREATE TABLE `lams`.`country_mstr` (
  `mstr_base_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mstr_base_id`),
  CONSTRAINT `fk_country_mstr_1`
    FOREIGN KEY (`mstr_base_id`)
    REFERENCES `lams`.`mstr_base` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


--  For State
 CREATE TABLE `lams`.`state_mstr` (
  `mstr_base_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `country_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`mstr_base_id`),
  INDEX `fk_state_mstr_2_idx` (`country_id` ASC),
  CONSTRAINT `fk_state_mstr_1`
    FOREIGN KEY (`mstr_base_id`)
    REFERENCES `lams`.`mstr_base` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_state_mstr_2`
    FOREIGN KEY (`country_id`)
    REFERENCES `lams`.`country_mstr` (`mstr_base_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



--  For City

 CREATE TABLE `lams`.`city_mstr` (
  `mstr_base_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `state_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`mstr_base_id`),
  INDEX `fk_city_mstr_2_idx` (`state_id` ASC),
  CONSTRAINT `fk_city_mstr_1`
    FOREIGN KEY (`mstr_base_id`)
    REFERENCES `lams`.`mstr_base` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_city_mstr_2`
    FOREIGN KEY (`state_id`)
    REFERENCES `lams`.`state_mstr` (`mstr_base_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

  
ALTER TABLE `lams`.`bank_mstr` 
ADD COLUMN `code` VARCHAR(45) NULL AFTER `is_active`;


ALTER TABLE `lams`.`country_mstr` 
DROP FOREIGN KEY `fk_country_mstr_1`;
ALTER TABLE `lams`.`country_mstr` 
CHANGE COLUMN `mstr_base_id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ;
ALTER TABLE `lams`.`country_mstr` 
ADD CONSTRAINT `fk_country_mstr_1`
  FOREIGN KEY (`id`)
  REFERENCES `lams`.`mstr_base` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
  ALTER TABLE `lams`.`state_mstr` 
DROP FOREIGN KEY `fk_state_mstr_1`;
ALTER TABLE `lams`.`state_mstr` 
CHANGE COLUMN `mstr_base_id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ;
ALTER TABLE `lams`.`state_mstr` 
ADD CONSTRAINT `fk_state_mstr_1`
  FOREIGN KEY (`id`)
  REFERENCES `lams`.`mstr_base` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  ALTER TABLE `lams`.`city_mstr` 
DROP FOREIGN KEY `fk_city_mstr_1`;
ALTER TABLE `lams`.`city_mstr` 
CHANGE COLUMN `mstr_base_id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ;
ALTER TABLE `lams`.`city_mstr` 
ADD CONSTRAINT `fk_city_mstr_1`
  FOREIGN KEY (`id`)
  REFERENCES `lams`.`mstr_base` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


