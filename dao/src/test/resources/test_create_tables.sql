DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_products;

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_address` varchar(255) NOT NULL,
  `user_age` varchar(255) DEFAULT NULL,
  `user_creation_date` bigint(20) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_login` varchar(255) NOT NULL UNIQUE ,
  `user_password` varchar(255) NOT NULL,
  `user_post_code` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_brand` varchar(255) NOT NULL,
  `product_creation_time` bigint(20) NOT NULL,
  `product_description` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_pic` varchar(255) DEFAULT NULL,
  `product_price` decimal(19,2) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `uk_product_name_and_brand` (`product_name`,`product_brand`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_creation_date` bigint(20) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_products` (
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;