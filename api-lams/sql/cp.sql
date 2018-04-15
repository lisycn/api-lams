ALTER TABLE `lams`.`applications` 
ADD COLUMN `is_from_cp` BIT(1) NULL DEFAULT FALSE AFTER `is_upload_complete`;

ALTER TABLE `lams`.`user` 
CHANGE COLUMN `invitation_count` `invitation_count` INT(3) UNSIGNED NULL DEFAULT 0 ;
