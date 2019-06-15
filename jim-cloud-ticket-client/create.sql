CREATE TABLE `ticket` (
`id`  bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`name`  varchar(30) CHARACTER SET utf8mb4 NOT NULL COMMENT '电影名称' ,
`lave_num`  int(5) UNSIGNED NOT NULL COMMENT '剩余票数' ,
`price`  decimal(10,0) UNSIGNED NOT NULL COMMENT '影票单价' ,
`start_time`  date NOT NULL COMMENT '电影开始时间' ,
`end_time`  date NOT NULL COMMENT '电影结束时间' ,
`status`  tinyint(2) UNSIGNED NULL DEFAULT 0 COMMENT '状态。0：有效；1：无效。' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_index` (`id`) USING BTREE
)
COMMENT='影票数据表'
;