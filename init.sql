-- Create the orders table if it doesn't exist
CREATE TABLE IF NOT EXISTS orders (
                                      id SERIAL PRIMARY KEY,
                                      customer_name VARCHAR(100) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING'
    );

-- Insert sample data only if the table is empty
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM orders) THEN
        INSERT INTO orders (customer_name, order_date, total_amount, status) VALUES
        ('Alice Nguyen', '2024-06-01 10:30:00', 250.75, 'COMPLETED'),
        ('Bob Tran', '2024-06-05 14:45:00', 120.00, 'PENDING'),
        ('Charlie Pham', '2024-06-07 09:15:00', 320.40, 'SHIPPED'),
        ('Diana Le', '2024-06-10 11:20:00', 89.99, 'CANCELLED'),
        ('Ethan Vo', '2024-06-12 16:10:00', 455.50, 'PROCESSING');
END IF;
END $$;
