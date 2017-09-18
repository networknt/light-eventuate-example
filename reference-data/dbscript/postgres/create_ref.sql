DROP table IF EXISTS ref_table;
DROP table IF EXISTS ref_value;
DROP table IF EXISTS value_locale;
DROP table IF EXISTS relation_type;
DROP table IF EXISTS relation;


CREATE TABLE ref_table (
  table_id             VARCHAR(160) NOT NULL,
  table_name           VARCHAR(80) NOT NULL,
  table_desc           VARCHAR(1024) NULL,
  host                 VARCHAR(32) NULL,
  active               VARCHAR(1) NOT NULL DEFAULT 'Y',
  editable             VARCHAR(1) NOT NULL DEFAULT 'Y',
  common               VARCHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY(table_id)
);


CREATE TABLE ref_value (
  value_id              VARCHAR(160) NOT NULL,
  table_id              VARCHAR(160) NOT NULL,
  value_code            VARCHAR(80) NOT NULL,
  start_time            TIMESTAMP NULL,
  end_time              TIMESTAMP NULL,
  display_order         INT,
  active                VARCHAR(1) NOT NULL,
  PRIMARY KEY(value_id)
);


CREATE TABLE value_locale (
  value_id              VARCHAR(160) NOT NULL,
  language              VARCHAR(2) NOT NULL,
  value_desc            VARCHAR(256) NULL,
  PRIMARY KEY(value_id,language)
);



CREATE TABLE relation_type (
  relation_id           VARCHAR(10) NOT NULL,
  relation_name         VARCHAR(32) NOT NULL,
  relation_desc         VARCHAR(256) NOT NULL,
  PRIMARY KEY(relation_id)
);



CREATE TABLE relation (
  relation_id           VARCHAR(10) NOT NULL,
  value_id_from         VARCHAR(160) NOT NULL,
  value_id_to           VARCHAR(160) NOT NULL,
  PRIMARY KEY(relation_id, value_id_from, value_id_to)
);

