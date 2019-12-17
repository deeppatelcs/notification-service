--
-- Extentsion for auto generating UUID.
--
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--
-- ID sequence for the notifications table.
--
CREATE SEQUENCE notifications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

--
-- Stores notification information.
--
CREATE TABLE notification (
    id BIGINT DEFAULT nextval('notifications_id_seq'::regclass) NOT NULL,
	notification_id uuid NOT NULL DEFAULT uuid_generate_v1(),
	sender_id VARCHAR(512) NOT NULL,
    recipient_id VARCHAR(512) NOT NULL,
    subject TEXT NOT NULL,
    message TEXT NOT NULL,
    seen BOOLEAN DEFAULT FALSE NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    date_created TIMESTAMP DEFAULT now() NOT NULL,
    severity_level INTEGER NOT NULL CHECK (severity_level > 0 AND severity_level < 6),
    notification_type VARCHAR(50) NOT NULL,
    data_location_url TEXT,
    PRIMARY KEY(id)
);