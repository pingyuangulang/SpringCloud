CREATE TABLE `user` (
`id`  bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`name`  varchar(10) CHARACTER SET utf8mb4 NOT NULL COMMENT '会员姓名' ,
`user_name`  varchar(10) CHARACTER SET utf8mb4 NOT NULL COMMENT '会员用户名' ,
`pwd`  varchar(10) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码' ,
`phone_num`  varchar(11) CHARACTER SET utf8mb4 NOT NULL COMMENT '会员手机号' ,
`balance`  decimal(10,0) UNSIGNED NULL DEFAULT 0 COMMENT '账户余额' ,
`level`  tinyint(2) UNSIGNED NULL DEFAULT 0 COMMENT '会员级别。0：初级会员；1：中级会员；2：高级会员；3：至尊会员' ,
`status`  tinyint(2) NULL DEFAULT 0 COMMENT '会员有效状态。0：有效；1：失效' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_index` (`id`) USING BTREE ,
UNIQUE INDEX `user_name_and_pwd_index` (`user_name`, `pwd`) USING BTREE
)
COMMENT='会员表'
;