CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT,
    Name VARCHAR(50),
    Quantity INT,
    Price DECIMAL(10, 2),
    Size VARCHAR(10),
    PRIMARY KEY (ProductID)
);

CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT,
    ProductID INT,
    TransactionType ENUM('Sale', 'Purchase'),
    Quantity INT,
    TransactionAmount DECIMAL(10, 2),
    TransactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (TransactionID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
