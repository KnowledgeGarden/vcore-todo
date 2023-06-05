-- ROLE corresponds to user in config.json
SET ROLE vcore;
-- Sequence for identifying ToDo entries
CREATE SEQUENCE IF NOT EXISTS public.item_seq
    AS bigint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.item (
	id 		BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('public.item_seq'::regclass),
	text    TEXT,
	etime	TIMESTAMP WITHOUT TIME ZONE
);

CREATE INDEX IF NOT EXISTS item_idx ON public.item (id, text);
