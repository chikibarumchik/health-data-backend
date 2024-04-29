CREATE TABLE dictionary(
                           id          BIGSERIAL PRIMARY KEY,
                           is_deleted  BOOLEAN DEFAULT false,
                           type        VARCHAR NOT NULL,
                           name        VARCHAR NOT NULL,
                           unit        VARCHAR(20) DEFAULT '',
                           code        VARCHAR DEFAULT NULL,
                           created_by  VARCHAR NOT NULL DEFAULT '',
                           modified_by VARCHAR NOT NULL DEFAULT '',
                           created_at  TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
                           modified_at TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL
);

INSERT INTO dictionary (type, name, unit, code)
VALUES ('measurement_type', 'Артериальное давление', 'мм рт. ст.', 'BLOOD_PRESSURE'),
       ('measurement_type', 'Частота сердечных сокращений', 'уд/мин', 'HEART_RATE'),
       ('measurement_type', 'Уровень сахара в крови', 'ммоль/л', 'BLOOD_GLUCOSE'),
       ('measurement_type', 'Температура тела', '°C', 'BODY_TEMPERATURE'),
       ('measurement_type', 'Уровень холестерина', 'ммоль/л', 'CHOLESTEROL_LEVELS'),
       ('measurement_type', 'Насыщение кислородом', '%', 'OXYGEN_SATURATION'),
       ('measurement_type', 'Вес', 'кг', 'WEIGHT'),
       ('measurement_type', 'Рост', 'см', 'HEIGHT'),
       ('measurement_type', 'Индекс массы тела', '', 'BODY_MASS_INDEX'),
       ('measurement_type', 'Частота дыхания', 'дых/мин', 'RESPIRATORY_RATE'),
       ('measurement_type', 'Уровень боли', '0-10', 'PAIN_LEVEL');
