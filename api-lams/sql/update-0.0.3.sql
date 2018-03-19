
CREATE TABLE `lams`.`document_mstr` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `lams`.`document_mapping_mstr` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `document_mstr_id` bigint(45) NOT NULL,
  `application_id` bigint(45) DEFAULT NULL,
  `user_id` bigint(45) NOT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (1,'PAN_CARD', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);

INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (2,'AADHAR_CARD', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);


INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (3,'LAST_3_MONTH_SALARY_SLIP', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);


INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (4,'LAST_6_MONTHS_BANK_ACCOUNT_STATEMENT', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);


INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (5,'FORM_16_OR_APPOIMENT_LETTER', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);


INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (6,'INVESTMENT_PROOFS', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);


INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (7,'EXISTING_LOAN_DOCUMENT', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);


INSERT INTO `lams`.`document_mstr` (`id`,`name`, `type`, `size`, `created_date`, `created_by`, `modified_date`, `modified_by`, `is_active`) 
VALUES (8,'OTHER_DOCUMENT', 'pdf,jpg,jpeg,png', '5', '2018-03-18 15:51:07', '1', '2018-03-18 15:51:07', '1', 1);

ALTER TABLE `lams`.`document_mapping_mstr` 
ADD COLUMN `original_name` VARCHAR(100) NULL AFTER `is_active`;



