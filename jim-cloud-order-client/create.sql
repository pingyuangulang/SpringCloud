CREATE TABLE `order_log` (
`id`  bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`user_id`  bigint(11) UNSIGNED NOT NULL COMMENT '会员id' ,
`ticket_id`  bigint(11) UNSIGNED NOT NULL COMMENT '影票id' ,
`amount`  int UNSIGNED NOT NULL COMMENT '购票数量' ,
`total_price`  decimal(10,0) UNSIGNED NOT NULL COMMENT '总价' ,
`create_time`  datetime NOT NULL COMMENT '购票时间' ,
`status`  tinyint(3) NULL DEFAULT 0 COMMENT '状态。0：有效；1：无效' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_index` (`id`) USING BTREE ,
UNIQUE INDEX `user_ticket_index` (`user_id`, `ticket_id`) USING BTREE
)
COMMENT='会员购票记录表'
;