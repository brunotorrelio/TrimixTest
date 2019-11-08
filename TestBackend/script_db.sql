CREATE SEQUENCE personas_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE personas
(
  per_id bigint NOT NULL,
  per_apellido character varying,
  per_nombre character varying,
  per_numero_documento bigint,
  per_tipo_documento character varying,
  per_fecha_nacimiento date,
  CONSTRAINT personas_pkey PRIMARY KEY (per_id)
);
