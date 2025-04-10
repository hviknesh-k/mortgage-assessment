CREATE TABLE IF NOT EXISTS `interest_rates` (
  `maturity_period` int NOT NULL,
  `interest_rate` DECIMAL(5,2) DEFAULT NULL,
  `last_updated` date DEFAULT current_timestamp(),
  PRIMARY KEY (`maturity_period`)
);