CREATE TABLE health_records
(
    id                BIGSERIAL PRIMARY KEY,
    user_id           BIGINT       NOT NULL,
    measurement_type  BIGINT references dictionary,
    measurement_value VARCHAR(100) NOT NULL,
    notes             VARCHAR(1000),
    is_deleted        BOOLEAN                  DEFAULT false,
    created_by        VARCHAR      NOT NULL    DEFAULT '',
    modified_by       VARCHAR      NOT NULL    DEFAULT '',
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    modified_at       TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL
);