CREATE TABLE public.cb_file
(
    id character varying(255) NOT NULL,
    createdate timestamp without time zone,
    fileid character varying(255),
    filename character varying(255),
    filepath character varying(255),
    CONSTRAINT cb_file_pkey PRIMARY KEY (id)
)
