DROP TABLE IF EXISTS `lunar_entity`;
CREATE TABLE IF NOT EXISTS `lunar_entity`
(
    `entity_name` varchar(255) NOT NULL DEFAULT '' COMMENT '实体名',
    `create_time` bigint       NOT NULL DEFAULT '0',
    `creator_id`  bigint       NOT NULL DEFAULT '0',
    `desc`        text         NOT NULL,
    PRIMARY KEY (`entity_name`),
    UNIQUE KEY (`entity_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `lunar_entity_schema`;
CREATE TABLE IF NOT EXISTS `lunar_entity_schema`
(
    `entity_name`   varchar(255) NOT NULL DEFAULT '' COMMENT '实体名',
    `field_name`    varchar(255) NOT NULL DEFAULT '' COMMENT '属性名',
    `field_type`    int          NOT NULL DEFAULT '0' COMMENT '属性类型',
    `jdbc_type`     int          NOT NULL DEFAULT '0' COMMENT '数据库类型',
    `default_value` varchar(255) NOT NULL DEFAULT '' COMMENT '默认值',
    `place_holder`  varchar(255) NOT NULL DEFAULT '' COMMENT '一个奇怪的值',
    `create_time`   bigint       NOT NULL DEFAULT '0',
    `creator_id`    bigint       NOT NULL DEFAULT '0',
    `desc`          text         NOT NULL,
    UNIQUE KEY (`entity_name`, `field_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;