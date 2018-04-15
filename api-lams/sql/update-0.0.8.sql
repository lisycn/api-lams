CREATE TABLE `lams`.`document_user_mapping_mstr` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `document_mstr_id` bigint(45) NOT NULL,
  `user_id` bigint(45) NOT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `original_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
