

-- 菜单表
create table sys_menu (
    menu_id             serial                  not null,                       -- 主键ID
    menu_name           varchar(50) not null,
    parent_id           int not null default 0,
    order_num           int,
    path                varchar(200),
    component           varchar(255),
    query               varchar(255),
    is_frame            int,
    is_cache            int not null default 0,
    menu_type           char,
    visible             char,
    status              int2,
    perms               varchar(100),
    icon                varchar(100),
    create_by           varchar(64),
    create_time         timestamp(6),
    update_by           varchar(64),
    update_time         timestamp(6),
    remark              varchar(500),
    CONSTRAINT sys_menu_pkey PRIMARY KEY (menu_id)
);


-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id                  serial                  not null,                       -- 主键ID
    area_id             varchar(32)             not null,                       -- 所属区划
    username            varchar(32)             not null,                       -- 用户名
    password            varchar(32)             not null,                       -- 密码
    phone               varchar(11)             not null,                       -- 手机号码
    salt                varchar(32)             not null,                       -- 盐
    role_id             int                     null,                           -- 角色ID
    gender              int2                    not null default 0,             -- 性别（0男；1女）
    avatar              varchar(32)             null,                           -- 头像
    status              int2                    not null default 0,             -- 状态（0启用；1禁用）
    create_time         timestamptz             not null default now(),         -- 创建时间
    CONSTRAINT pk_t_user PRIMARY KEY (id)
);


-- 角色表
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (
    id                  serial                  not null,                       -- 主键ID
    area_id             varchar(32)             not null,                       -- 所属区划
    name                varchar(32)             not null,                       -- 站点名称
    code                varchar(9)              not null,                       -- 区划编码
    parent_id           varchar(32)             null,                           -- 主键ID
    level               int2                    not null,                       -- 级别
    province            varchar(32)             not null,                       -- 省
    city                varchar(32)             null,                           -- 市
    county              varchar(32)             null,                           -- 区县
    town                varchar(32)             null,                           -- 乡镇
    CONSTRAINT pk_t_role PRIMARY KEY (id)
);


-- 权限表
DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission (
    id                  serial                  not null,                       -- 主键ID
    area_id             varchar(32)             not null,                       -- 所属区划
    name                varchar(32)             not null,                       -- 站点名称
    code                varchar(9)              not null,                       -- 区划编码
    parent_id           varchar(32)             null,                           -- 主键ID
    level               int2                    not null,                       -- 级别
    province            varchar(32)             not null,                       -- 省
    city                varchar(32)             null,                           -- 市
    county              varchar(32)             null,                           -- 区县
    town                varchar(32)             null,                           -- 乡镇
    CONSTRAINT pk_t_permission PRIMARY KEY (id)
);


-- 用户角色关系表
r_user_role


-- 角色权限关系表
r_role_permission



-- 区划表
DROP TABLE IF EXISTS t_area;
CREATE TABLE t_area (
    id                  varchar(32)             not null,                       -- 主键ID
    code                varchar(9)              not null,                       -- 区划编码
    parent_id           varchar(32)             null,                           -- 主键ID
    level               int2                    not null,                       -- 级别
    province            varchar(32)             not null,                       -- 省
    city                varchar(32)             null,                           -- 市
    county              varchar(32)             null,                           -- 区县
    town                varchar(32)             null,                           -- 乡镇
    CONSTRAINT pk_t_area PRIMARY KEY (id)
);


-- 站点
DROP TABLE IF EXISTS t_station;
CREATE TABLE t_station (
    id                  serial                  not null,                       -- 主键ID
    area_id             varchar(32)             not null,                       -- 所属区划
    code                varchar(32)             not null,                       -- 站点编号
    name                varchar(128)            not null,                       -- 站点名称
    lng                 varchar(16)             null,                           -- 经度
    lat                 varchar(16)             null,                           -- 纬度
    remark              varchar(256)            null,                           -- 备注
    user_id             int                     null,                           -- 站点负责人
    create_time         timestamptz             not null default now(),         -- 创建时间
    CONSTRAINT pk_t_station PRIMARY KEY (id)
);


-- 设备
DROP TABLE IF EXISTS t_equipment;
CREATE TABLE t_equipment (
    id                  serial                  not null,                       -- 主键ID
    area_id             varchar(32)             null,                           -- 所属区划
    station_id          integer                 null,                           -- 所属站点
    name                varchar(128)            not null,                       -- 设备名称
    model               varchar(128)            null,                           -- 设备型号
    code                varchar(32)             null,                           -- 设备编码
    lng                 varchar(16)             null,                           -- 经度
    lat                 varchar(16)             null,                           -- 纬度
    status              int2                    not null default 0,             -- 状态(0离线；1在线)
    address             varchar(128)            null,                           -- 设备安装地址
    ip                  varchar(16)             null,                           -- 设备IP地址
    cal                 varchar(16)             null,                           -- 标定值
    remark              varchar(256)            null,                           -- 备注
    create_time         timestamptz             not null default now(),         -- 创建时间
    CONSTRAINT pk_t_equipment PRIMARY KEY (id)
);



-- 附件
DROP TABLE IF EXISTS t_atachment;
CREATE TABLE t_atachment (
    id                  serial                  not null,                       -- 主键ID
    obj                 varchar(16)             not null,                       -- 对象
    obj_id              int                     not null,                       -- 对象ID
    type                int2                    not null default 0,             -- 附件类型(0图片；1视频；)
    name                varchar(128)            null,                           -- 附件名称
    remark              varchar(256)            null,                           -- 备注
    create_time         timestamptz             not null default now(),         -- 创建时间
    CONSTRAINT pk_t_atachment PRIMARY KEY (id)
);



-- 监测因子数据
DROP TABLE IF EXISTS t_data;
CREATE TABLE t_data (
    id                  serial                  not null,                       -- 主键ID
    temperature         varchar(16)             null,                           -- 空气温度
    humidity            varchar(16)             null,                           -- 空气湿度
    pressure            varchar(16)             null,                           -- 大气压
    noraindays          varchar(16)             null,                           -- 降雨量
    illuminance         varchar(16)             null,                           -- 光照度
    speed               varchar(16)             null,                           -- 风速
    direct              varchar(16)             null,                           -- 风向
    temperature1        varchar(16)             null,                           -- 土壤温度
    humidity1           varchar(16)             null,                           -- 土壤湿度
    water               varchar(16)             null,                           -- 凋落物含水率
    jx                  varchar(16)             null,                           -- 积雪
    fq                  varchar(16)             null,                           -- 返青
    x                   varchar(16)             null,                           -- x
    y                   varchar(16)             null,                           -- y
    z                   varchar(16)             null,                           -- z
    create_time         timestamptz             not null default now(),         -- 创建时间
    CONSTRAINT pk_t_data PRIMARY KEY (id)
);


    -- province            varchar(16),        -- 省
    -- city                varchar(16),        -- 市
    -- county              varchar(16),        -- 区县



