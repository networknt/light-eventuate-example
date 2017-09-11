DROP table IF EXISTS ref_table;
DROP table IF EXISTS ref_value;
DROP table IF EXISTS value_locale;
DROP table IF EXISTS relation_type;
DROP table IF EXISTS relation;


CREATE TABLE ref_table (
  table_id             VARCHAR2(160) NOT NULL,
  table_name           VARCHAR2(80) NOT NULL,
  table_desc           VARCHAR2(1024) NULL,
  host                 VARCHAR2(32) NULL,
  active               VARCHAR2(1) NOT NULL DEFAULT 'Y',
  editable             VARCHAR2(1) NOT NULL DEFAULT 'Y',
  common               VARCHAR2(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY(table_id)
);


CREATE TABLE ref_value (
  value_id              VARCHAR2(160) NOT NULL,
  table_id              VARCHAR2(160) NOT NULL,
  value_code            VARCHAR2(80) NOT NULL,
  start_time            TIMESTAMP NULL,
  end_time              TIMESTAMP NULL,
  display_order         INT,
  active                VARCHAR2(1) NOT NULL,
  PRIMARY KEY(value_id)
);


CREATE TABLE value_locale (
  value_id              VARCHAR2(160) NOT NULL,
  language              VARCHAR2(2) NOT NULL,
  value_desc            VARCHAR2(256) NULL,
  PRIMARY KEY(value_id,language)
);



CREATE TABLE relation_type (
  relation_id           VARCHAR2(10) NOT NULL,
  relation_name         VARCHAR2(32) NOT NULL,
  relation_desc         VARCHAR2(256) NOT NULL,
  PRIMARY KEY(relation_id)
);



CREATE TABLE relation (
  relation_id           VARCHAR2(10) NOT NULL,
  value_id_from         VARCHAR2(160) NOT NULL,
  value_id_to           VARCHAR2(160) NOT NULL,
  PRIMARY KEY(relation_id, value_id_from, value_id_to)
);

