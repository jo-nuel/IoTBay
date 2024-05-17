-- iotbay.supplier definition

CREATE TABLE `supplier` (
  `supplierID` int AUTO_INCREMENT NOT NULL,
  `supplierName` varchar(50) DEFAULT NULL,
  `emailAddress` varchar(30) NULL,
  `phoneNum` varchar(10) DEFAULT NULL,
  `recordActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4;

-- iotbay.user

CREATE TABLE IoTBay.`user` (
	`userID` INT auto_increment NOT NULL,
	`userName` varchar(20) NULL,
	`userEmail` varchar(30) NULL,
	`password` varchar(20) NULL,
	`userType` varchar(10) NULL,
  PRIMARY KEY (`userID`)
)
ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4;