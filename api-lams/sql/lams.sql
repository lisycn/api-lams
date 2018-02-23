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





INSERT INTO `lams`.`user` (`name`, `email`, `mobile`, `password`,  `is_active`) VALUES ('admin', 'admin@gmail.com', '8975849586', 'e8e99ce7fa9c15f522e21e2fb2cc752c', 1);
