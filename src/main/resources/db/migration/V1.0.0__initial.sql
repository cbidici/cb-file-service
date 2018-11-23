
DROP TABLE IF EXISTS cb_file;
CREATE TABLE cb_file (
    id character varying(255) NOT NULL,
    file_id character varying(255),
    name character varying(255),
    path character varying(255),
    url character varying(255)
);

ALTER TABLE cb_file DROP CONSTRAINT IF EXISTS cb_file_pkey;
ALTER TABLE ONLY cb_file
    ADD CONSTRAINT cb_file_pkey PRIMARY KEY (id);
