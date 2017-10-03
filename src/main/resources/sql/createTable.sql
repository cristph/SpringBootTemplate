CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `role` enum('USER','ADMIN','SUPERADMIN') NOT NULL DEFAULT 'USER' COMMENT '角色(''普通用户'',''管理员'',''超级管理员'')',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_operator` bigint(20) DEFAULT '-1' COMMENT '执行创建操作的管理员id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_operator` bigint(20) DEFAULT '-1' COMMENT '执行最后更新操作的管理员id',
  `delete_time` datetime DEFAULT NULL COMMENT '删除的时间',
  `delete_operator` bigint(20) DEFAULT '-1' COMMENT '执行删除操作的管理员id',
  `delete_flag` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除标记（0未删除，1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';