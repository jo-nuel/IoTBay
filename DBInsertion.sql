INSERT INTO iotbay.devices (deviceName,devicePrice,deviceDesc,deviceStock,deviceAvailability,deviceCategory,deviceBrand,deviceImageURL) VALUES
	 ('Smartphone Alpha',500.0,'Latest model with 5G capabilities',10,1,'Smartphones','AlphaTech','/images/smartphoneAlpha.jpg'),
	 ('Tablet Beta',349.99,'10-inch display perfect for media consumption',15,0,'Tablets','BetaElectronics','/images/tabletBeta.webp'),
	 ('Laptop Gamma',399.99,'Lightweight laptop with 10 hours battery life',20,1,'Laptops','GammaGear','/images/laptopGamma.webp'),
	 ('E-Reader Delta',449.99,'E-ink reader ideal for avid book lovers',25,1,'Mobile Accessories','DeltaDevices','/images/e-reader.jpg'),
	 ('Smartwatch Epsilon',499.99,'Tracks health and fitness activities',30,0,'Wearables','EpsilonWear','/images/smartwatch.webp'),
	 ('Game Console Zeta',549.99,'Provides next-gen gaming experience',35,1,'Gaming Consoles','ZetaPlay','/images/gameConsole.jpg'),
	 ('Portable Speaker Eta',599.99,'High-quality audio on the go',40,1,'Mobile Accessories','EtaSound','/images/portableSpeaker.webp'),
	 ('Action Camera Theta',649.99,'Durable camera for extreme sports',45,0,'Mobile Accessories','ThetaTech','/images/actionCamera.jpg'),
	 ('Smart Thermostat Iota',699.99,'Automates home temperature for efficiency',50,1,'Home Automation','IotaHome','/images/smartThermostat.jpg'),
	 ('Fitness Tracker Kappa',749.99,'Monitors steps, heart rate, and sleep',55,1,'Wearables','KappaFit','/images/fitnessTracker.jpg');
INSERT INTO iotbay.devices (deviceName,devicePrice,deviceDesc,deviceStock,deviceAvailability,deviceCategory,deviceBrand,deviceImageURL) VALUES
	 ('Wireless Earphones Lambda',799.99,'Noise cancelling and long-lasting battery',60,0,'Mobile Accessories','LambdaAudio','/images/wirelessEarphone.jpg'),
	 ('VR Headset Mu',849.99,'Immersive virtual reality experience',65,1,'Gaming Consoles','MuVision','/images/vrHeadset.jpg'),
	 ('Drone Nu',899.99,'For aerial photography and videography',70,1,'Gaming Consoles','NuFly','/images/drone.jpg'),
	 ('Digital Camera Xi',949.99,'High resolution camera for professional photography',75,0,'Mobile Accessories','XiOptics','/images/digitalCamera.jpeg'),
	 ('Robot Vacuum Omicron',999.99,'Automated cleaning with smart mapping',80,1,'Home Automation','OmicronClean','/images/robotVacuum.jpeg'),
	 ('3D Printer Pi',1049.99,'Creates 3D objects from digital designs',85,1,'Laptops','PiPrint','/images/3dPrinter.webp'),
	 ('Smart Refrigerator Rho',1099.99,'Keeps food fresh and tracks groceries',90,0,'Home Automation','RhoFridge','/images/smartFridge.jpeg'),
	 ('Gaming Laptop Sigma',1149.99,'High performance for gaming and graphic design',95,1,'Laptops','SigmaComputing','/images/gamingLaptop.webp'),
	 ('Smart Oven Tau',1199.99,'Cooks automatically with preset recipes',100,1,'Home Automation','TauKitchen','/images/smartOven.jpeg'),
	 ('Home Security System Upsilon',1249.99,'Monitors and protects your home remotely',105,0,'Home Automation','UpsilonSecure','/images/homeSecurity.jpeg');


--INSERT CUSTOMERS

INSERT INTO iotbay.user (userName, userEmail, password, userType) VALUES
	("Margaret Murray", "Margaret.Murray@gmail.com", "trench", "Customer"),
	("Janette Mcintyre", "Janette.Mcintyre@hotmail.com", "window", "Customer"),
	("Jackson Vance", "Jackson.Vance@outlook.com", "domestic", "Customer"),
	("Tamika Barron", "Tamika.Barron@outlook.com", "opponent", "Customer"),
	("Gregg Simmons", "Gregg.Simmons@gmail.com", "shed", "Customer"),
	("Monica Bowers", "Monica.Bowers@gmail.com", "underline", "Customer"),
	("Sid Duran", "Sid.Duran@hotmail.com", "clinic", "Customer"),
	("Irwin Sanchez", "Irwin.Sanchez@outlook.com", "survival", "Customer"),
	("Florencio Griffith", "Florencio.Griffith@gmail.com", "despair", "Customer"),
	("Gwen Harper", "Gwen.Harper@gmail.com", "sand", "Customer"),
	("Sol Duke", "Sol.Duke@hotmail.com", "advice", "Customer"),
	("Hollis Zamora", "Hollis.Zamora@gmail.com", "confusion", "Customer"),
	("Carla Ramsey", "Carla.Ramsey@gmail.com", "intelligence", "Customer"),
	("Lillian Hensley", "Lillian.Hensley@hotmail.com", "professional", "Customer"),
	("Derick Hopkins", "Derick.Hopkins@gmail.com", "public", "Customer"),
	("Noble Rojas", "Noble.Rojas@gmail.com", "drug", "Customer"),
	("Mohammad Lewis", "Mohammad.Lewis@hotmail.com", "production", "Customer"),
	("Yesenia Curry", "Yesenia.Curry@hotmail.com", "white", "Customer"),
	("Lowell David", "Lowell David", "whole", "Customer"),
	("Gilda Yates", "Gilda.Yates@hotmail.com", "salvation", "Customer")

INSERT INTO iotbay.customer (userID, customerType, shippingAddress, accountActive) VALUES
	(1, "Individual", "39 Bungana Drive", true),
	(2, "Individual", "20 Healy Road", true),
	(3, "Individual", "84 Rockhampton", true),
	(4, "Individual", "33 Mills Street", true),
	(5, "Individual", "1 Purcell Place", true),
	(6, "Individual", "54 Lapko Road", true),
	(7, "Individual", "4 Friar John Way", true),
	(8, "Individual", "29 Yangan Drive", true),
	(9, "Individual", "5 Carolina Park Road", true),
	(10, "Company", "41 Austin Road", true),
	(11, "Individual", "34 Hereford Avenue", true),
	(12, "Individual", "26 Commercial Street", true),
	(13, "Individual", "32 Cornish Stree", true),
	(14, "Company", "97 Chatsworth Drive", true),
	(15, "Company", "1 Eurack Court", true),
	(16, "Individual", "49 Henley Beach Road", true),
	(17, "Individual", "52 Gadd Avenue", true),
	(18, "Individual", "5 Webb Road", true),
	(19, "Individual", "24 Darwinia Loop", false),
	(20, "Individual", "28 Tapleys Hill Road", false);

INSERT INTO iotbay.supplier (supplierName,emailAddress,phoneNum,recordActive) VALUES
('TechD', 'techd@example.com', '1234567890', 1),
('ProsHardware', 'prosh@example.com', '0987654321', 1),
('ITGadget', 'itgadget@example.com', '1234567890', 0),
('BrightTech', 'brighttech@example.com', '0987654321', 0),
('QuantumDevice', 'quantum@example.com', '1234567890', 1),
('GlobalTech', 'globaltech@example.com', '0987654321', 1),
('FutureTech', 'futureitech@example.com', '1234567890', 0),
('Pinnacle', 'pinnacle@example.com', '0987654321', 0),
('Apex Hardware', 'apexhd@example.com', '1234567890', 1),
('Alpha Technology', 'alphat@example.com', '0987654321', 1),
('OmegaSolutions', 'omegas@example.com', '1234567890', 0),
('Zenith', 'zenith@example.com', '0987654321', 0),
('PrimeIT', 'primeit@example.com', '1234567890', 1),
('PeakTech', 'peaktech@example.com', '0987654321', 1),
('VertexIT', 'vertexit@example.com', '1234567890', 0),
('Summit Techology', 'summit@example.com', '0987654321', 1),
('PinnacleDevice', 'pinnacledevice@example.com', '1234567890', 1),
('ApexITDevice', 'apexit@example.com', '0987654321', 1),
('ZenithHardware', 'zenithit@example.com', '1234567890', 1),
('Prime Tech', 'primetech@example.com', '0987654321', 0);

