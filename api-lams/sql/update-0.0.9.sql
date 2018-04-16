
ALTER TABLE `lams`.`address_mstr` 
ADD COLUMN `building_name` VARCHAR(100) NULL AFTER `add_type`,
ADD COLUMN `premises_number` VARCHAR(100) NULL AFTER `building_name`;
