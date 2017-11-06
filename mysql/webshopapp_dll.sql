DROP SCHEMA IF EXISTS `webshopapp`;
CREATE SCHEMA `webshopapp` DEFAULT CHARACTER SET utf8 ;
USE `webshopapp`;

CREATE TABLE `webshopapp`.`customer` (
    `customer_id` BIGINT NOT NULL AUTO_INCREMENT, 
    `name` VARCHAR(255) NOT NULL,
	`phone` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`customer_id`)
)
COMMENT = 'Webshop Customers.';

CREATE TABLE `webshopapp`.`address` (
	`address_id` BIGINT NOT NULL AUTO_INCREMENT,
	`customer_id` BIGINT NOT NULL,
	`type` VARCHAR(20) NOT NULL, 
    `country` VARCHAR(50) NOT NULL,
    `city` VARCHAR(50) NOT NULL,	
	`zipcode` VARCHAR(50) NOT NULL,
	`street` VARCHAR(100) NOT NULL,
	`house_number` VARCHAR(255) NOT NULL,
	`comment` VARCHAR(400) NULL,
    PRIMARY KEY (address_id),
	INDEX `idx_address_customer_id` (`customer_id` ASC)
)
COMMENT = 'Customer Addresses.';	

ALTER TABLE `webshopapp`.`address` ADD CONSTRAINT `address_fk_customer`
FOREIGN KEY (`customer_id`) REFERENCES `webshopapp`.`customer` (`customer_id`);