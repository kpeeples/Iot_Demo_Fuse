
CREATE TABLE "temperature" (
    pid                  SERIAL PRIMARY KEY,
    deviceID            DECIMAL,
    timestamp            VARCHAR,
    value                INTEGER,
    average		 REAL
);

ALTER TABLE public."temperature" OWNER TO psteiner;

CREATE SEQUENCE "temperature_pid_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."temperature_pid_seq1" OWNER TO psteiner;
ALTER SEQUENCE "temperatureRate_pid_seq1" OWNED BY "temperature".pid;
ALTER TABLE ONLY "temperature" ALTER COLUMN pid SET DEFAULT nextval('"temperature_pid_seq1"'::regclass);
ALTER TABLE ONLY "temperature"
    ADD CONSTRAINT "temperature_pkey" PRIMARY KEY (pid);
