-- SQL script for the initial database schema setup following best practices

-- Create transactions table
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY, -- Unique identifier for each transaction
    tx_hash VARCHAR(255) NOT NULL UNIQUE, -- Transaction hash, must be unique
    status VARCHAR(50) NOT NULL CHECK (status IN ('pending', 'confirmed', 'failed')), -- Status of the transaction with constraints
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- Timestamp of when the transaction was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL -- Timestamp of when the transaction was last updated
);

-- Create index on tx_hash for faster lookups
CREATE INDEX idx_tx_hash ON transactions(tx_hash);

-- Create trigger to update the updated_at column on row update
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_updated_at
BEFORE UPDATE ON transactions
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
