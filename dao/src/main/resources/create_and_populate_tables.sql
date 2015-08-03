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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_brand` varchar(255) NOT NULL,
  `product_creation_time` bigint(20) NOT NULL,
  `product_description` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_pic` varchar(255) DEFAULT NULL,
  `product_price` decimal(19,2) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_creation_date` bigint(20) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `order_products` (
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO products
(product_id, product_name, product_brand, product_description, product_price, product_pic, product_creation_time)
VALUES
  (1, 'revolver', 'colt', 'good gun for gunslinger', 20.5, 'pic1', 1863),
  (2, 'rifle', 'colt', 'long range rifle', 40, 'pic2', 1864),
  (3, 'shotgun', 'colt', 'short range rifle', 30.5, 'pic3', 1865),
  (4, 'hat', 'unknown', 'protects from sun-hit', 2.5, 'pic4', 1866);

INSERT INTO users
(user_id, user_login, user_password, user_email, user_first_name, user_last_name, user_post_code, user_address,
user_age, user_creation_date)
VALUES
  (1, 'admin', 'admin', 'admin@gmail.com', 'adm', 'in', '123', '123', '123', 1234567),
  (2, 'manager', 'manager', 'manager@gmail.com', 'mana', 'ger', '123', '123', '123', 1234567),
  (3, 'user', 'user', 'user@gmail.com', 'us', 'er', '123', '123', '123', 1234567),
  (4, 'user1', 'user1', 'user1@gmail.com', 'us', 'er', '123', '123', '123', 1234567),
  (5, 'user12', 'user12', 'user12@gmail.com', 'us', 'er', '123', '123', '123', 1234567),
  (6, 'user123', 'user123', 'user123@gmail.com', 'us', 'er', '123', '123', '123', 1234567);

INSERT INTO user_roles
(role_id, user_name, user_role)
VALUES
  (1, 'admin', 'ROLE_ADMIN'),
  (2, 'manager', 'ROLE_MANAGER'),
  (3, 'user', 'ROLE_USER'),
  (4, 'user1', 'ROLE_USER'),
  (5, 'user12', 'ROLE_USER'),
  (6, 'user123', 'ROLE_USER');