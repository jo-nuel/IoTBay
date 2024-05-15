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

-- iotbay.user

CREATE TABLE IoTBay.`user` (
	userID INT auto_increment NOT NULL,
	userName varchar(100) NULL,
	userEmail varchar(100) NULL,
	password varchar(100) NULL,
	userType varchar(100) NULL,
	CONSTRAINT user_pk PRIMARY KEY (userID)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- iotbay.customer

CREATE TABLE IoTBay.customer (
	userID INT NULL,
	customerType varchar(100) NULL,
	shippingAddress varchar(100) NULL,
	accountActive TINYINT NULL,
	CONSTRAINT customer_user_FK FOREIGN KEY (userID) REFERENCES IoTBay.`user`(userID)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- iotbay.Payment definition

CREATE TABLE `Payment` (
  `paymentID` int NOT NULL,
  `paymentType` varchar(100) DEFAULT NULL,
  `cardName` varchar(100) DEFAULT NULL,
  `cardNumber` varchar(100) DEFAULT NULL,
  `cardExpiryDate` varchar(100) DEFAULT NULL,
  `cardCvv` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`paymentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;