INSERT
INTO `lunar_entity` (`entity_name`, `create_time`, `creator_id`, `desc`)
VALUES ('department', `EXTRACT`(EPOCH FROM CURRENT_TIMESTAMP()), '1', 'desc');

INSERT
INTO `lunar_entity_schema` (`entity_name`, `field_name`,
                            `field_type`, `jdbc_type`, `default_value`, `place_holder`,
                            `create_time`, `creator_id`, `desc`)
VALUES ('department', 'name', '4', '1', '', 'a',
        `EXTRACT`(EPOCH FROM CURRENT_TIMESTAMP()), 1, 'desc'),
       ('department', 'order', '1', '5', '', 'a',
        `EXTRACT`(EPOCH FROM CURRENT_TIMESTAMP()), 1, 'desc'),
       ('department', 'create_time', '2', '6', '', 'a',
        `EXTRACT`(EPOCH FROM CURRENT_TIMESTAMP()), 1, 'desc');