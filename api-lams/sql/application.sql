


ALTER TABLE `lams`.`user` 
DROP FOREIGN KEY `fk_user_mstr_address`;
ALTER TABLE `lams`.`user` 
DROP COLUMN `address`,
CHANGE COLUMN `is_email_verified` `is_email_verified` BIT(1) NULL DEFAULT NULL AFTER `birth_date`,
DROP INDEX `fk_user_mstr_address` ;



ALTER TABLE `lams`.`user` 
ADD COLUMN `communication_add` BIGINT(20) NOT NULL AFTER `salutation`,
ADD COLUMN `permanent_add` BIGINT(20) NULL AFTER `communication_add`,
ADD COLUMN `pan_card` VARCHAR(45) NULL AFTER `permanent_add`,
ADD COLUMN `aadhar_card_no` VARCHAR(45) NULL AFTER `pan_card`,
ADD COLUMN `edu_qualification` VARCHAR(45) NULL AFTER `aadhar_card_no`,
ADD COLUMN `contact_number` VARCHAR(45) NULL AFTER `edu_qualification`;


ALTER TABLE `lams`.`user` 
CHANGE COLUMN `communication_add` `communication_add` BIGINT(20) NULL ;

ALTER TABLE `lams`.`user` 
ADD CONSTRAINT `fk_user_comm_add`
  FOREIGN KEY (`communication_add`)
  REFERENCES `lams`.`address_mstr` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `lams`.`user` 
ADD CONSTRAINT `fk_user_perm_add`
  FOREIGN KEY (`permanent_add`)
  REFERENCES `lams`.`address_mstr` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `lams`.`user` 
ADD COLUMN `is_same_us_address` BIT(1) NULL AFTER `contact_number`;

ALTER TABLE `lams`.`address_mstr` 
ADD COLUMN `add_type` INT NULL AFTER `user_id`;

ALTER TABLE `lams`.`address_mstr` 
DROP COLUMN `state_id`,
DROP COLUMN `country_id`;


ALTER TABLE `lams`.`address_mstr` 
CHANGE COLUMN `pincode` `pincode` VARCHAR(45) NULL ,
CHANGE COLUMN `city_id` `city_id` BIGINT(20) UNSIGNED NULL ,
CHANGE COLUMN `is_active` `is_active` BIT(1) NOT NULL ;

CREATE TABLE `lams`.`application_type_mstr` (
  `id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`)),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

  CREATE TABLE `lams`.`loan_type_mstr` (
  `id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));


CREATE TABLE `applications` (
  `id` bigint(45) NOT NULL,
  `application_type_id` bigint(20) DEFAULT NULL,
  `loan_type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `outstanding_amount` double DEFAULT NULL,
  `emi` double DEFAULT NULL,
  `balance_tenure` double DEFAULT NULL,
  `close_before_disbsmnt` bigint(20) DEFAULT NULL,
  `tenure` int(11) DEFAULT NULL,
  `property_cost` double DEFAULT NULL,
  `property_address` varchar(200) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `bank_acc_number` varchar(45) DEFAULT NULL,
  `loan_amount` double DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_application_type_mst_idx` (`application_type_id`),
  KEY `fk_loan_type_mst_idx` (`loan_type_id`),
  CONSTRAINT `fk_application_type_mst` FOREIGN KEY (`application_type_id`) REFERENCES `application_type_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_type_mst` FOREIGN KEY (`loan_type_id`) REFERENCES `loan_type_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `lams`.`applications` 
CHANGE COLUMN `id` `id` BIGINT(45) NOT NULL AUTO_INCREMENT ;














INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Home Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Loan Against Property', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Secured Business Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Working Capital Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Education Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Car Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('OverDraft Facilities', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('DropLine OverDraft Facilities', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Bank Guarantee', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('CC Facilities', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Term Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Loan Against FDs', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Loan Against Securities', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Project Finance', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Private Equity Finance', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Gold Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Others', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Existing Loans', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Current Loan', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Bank Accounts', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES ('Closed Loans', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');



INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (5);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (6);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (7);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (8);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (9);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (10);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (11);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (12);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (13);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (14);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (15);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (16);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (17);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (18);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (19);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (20);
INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES (21);


INSERT INTO `lams`.`loan_type_mstr` (`id`) VALUES ('22');
INSERT INTO `lams`.`loan_type_mstr` (`id`) VALUES ('23');
INSERT INTO `lams`.`loan_type_mstr` (`id`) VALUES ('24');
INSERT INTO `lams`.`loan_type_mstr` (`id`) VALUES ('25');










