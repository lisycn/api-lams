INSERT INTO `lams`.`notification_template` (`created_by`, `created_date`, `is_active`, `modified_by`, `modified_date`, `alias`, `name`, `notification_type`) VALUES ('1', NOW(), 1, 1, NOW(), 'SMS', 'sms.ftl', 'SMS');
INSERT INTO `lams`.`mstr_base` (`name`, `code`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES ('Registration', 'RG', 1, 1, NOW(), 1, NOW());
INSERT INTO `lams`.`otp_type_master` (`id`) VALUES ('26');

