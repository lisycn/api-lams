


ALTER TABLE `lams`.`user` 
ADD COLUMN `employment_type` BIGINT(20) NULL AFTER `is_active`,
ADD COLUMN `employer_name` VARCHAR(45) NULL AFTER `employment_type`,
ADD COLUMN `employment_address` VARCHAR(45) NULL AFTER `employer_name`,
ADD COLUMN `gross_monthly_income` DOUBLE NULL AFTER `employment_address`,
ADD COLUMN `total_work_experience` INT NULL AFTER `gross_monthly_income`,
ADD COLUMN `self_employed_type` BIGINT(20) NULL AFTER `total_work_experience`,
ADD COLUMN `entity_name` VARCHAR(45) NULL AFTER `self_employed_type`,
ADD COLUMN `entity_type` BIGINT(20) NULL AFTER `entity_name`;





INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (5,'Home Loans', 'HL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (6,'Loan Against Property', 'LAP', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (7,'Secured Business Loans', 'SBL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (8,'Working Capital Loans', 'WC', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (9,'Education Loans', 'EL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (10,'Car Loans', 'CL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (11,'OverDraft Facilities', 'ODL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (12,'DropLine OverDraft Facilities', 'DLOF', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (13,'Bank Guarantee', 'BG', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (14,'CC Facilities', 'CCF', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (15,'Term Loans', 'TL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (16,'Loan Against FDs', 'LAF', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (17,'Loan Against Securities', 'LAS', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (18,'Project Finance', 'PFL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (19,'Private Equity Finance', 'PEF', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (20,'Gold Loans', 'GL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (21,'Others', 'OL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (22,'Existing Loans', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (23,'Current Loan', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (25,'Closed Loans', NULL, 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');



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








ALTER TABLE `lams`.`applications` 
DROP COLUMN `property_address`,
DROP COLUMN `property_cost`;

ALTER TABLE `lams`.`applications` 
ADD COLUMN `lead_reference_no` VARCHAR(100) NOT NULL AFTER `is_active`;





CREATE TABLE `lams`.`home_loan_details` (
  `id` bigint(20) NOT NULL,
  property_cost double default null,
  property_address varchar(100) default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;





CREATE TABLE `lams`.`loan_against_property_details` (
  `id` bigint(20) NOT NULL,
  property_market_value double default null,
  property_address varchar(100) default null,
  reason_for_lap TEXT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `lams`.`personal_loan_details` (
  `id` bigint(20) NOT NULL,
  reason_for_pl TEXT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`secured_business_loan_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `lams`.`working_capital_loan_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
	

CREATE TABLE `lams`.`education_loan_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `lams`.`car_loan_details` (
  `id` bigint(20) NOT NULL,
  `car_cost` double default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `lams`.`overDraft_facilities_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `lams`.`dropLine_overDraft_facilities_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `lams`.`bank_guarantee_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `lams`.`cc_facilities_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`term_loan_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`loan_againstfds_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `lams`.`loan_against_securities_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`project_finance_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`private_equity_finance_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`gold_loan_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `lams`.`others_details` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `lams`.`mstr_base` (`id`,`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) 
VALUES (27,'Personal Loan', 'PL', 1, 1, '2018-03-11 23:26:49', 1, '2018-03-11 23:26:49');

INSERT INTO `lams`.`application_type_mstr` (`id`) VALUES ('27');

UPDATE `lams`.`mstr_base` SET `name`='New Loan' WHERE `id`='23';




