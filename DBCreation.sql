-- iotbay.devices definition

CREATE TABLE `devices` (
  `deviceID` int NOT NULL AUTO_INCREMENT,
  `deviceName` varchar(100) DEFAULT NULL,
  `devicePrice` double DEFAULT NULL,
  `deviceDesc` varchar(100) DEFAULT NULL,
  `deviceStock` int DEFAULT NULL,
  `deviceAvailability` tinyint(1) DEFAULT NULL,
  `deviceCategory` varchar(100) DEFAULT NULL,
  `deviceBrand` varchar(100) DEFAULT NULL,
  `deviceImageURL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`deviceID`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;