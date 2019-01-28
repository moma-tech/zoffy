create table `open-api`.auth_rel_role_resource
(
  auth_role_id char(32) not null,
  auth_resource_id char(32) not null,
  id char(32) not null,
  status tinyint(3) default 0 not null,
  create_time datetime default CURRENT_TIMESTAMP not null,
  update_time datetime null,
  modifier char(32) null,
  primary key (auth_role_id, auth_resource_id, id)
);

create index fk_auth_role_has_auth_resource_auth_resource1_idx
  on `open-api`.auth_rel_role_resource (auth_resource_id);

create index fk_auth_role_has_auth_resource_auth_role1_idx
  on `open-api`.auth_rel_role_resource (auth_role_id);

create table `open-api`.auth_rel_user_role
(
  auth_user_id char(32) not null,
  auth_role_id char(32) not null,
  id char(32) not null,
  status tinyint(3) default 0 not null,
  create_time datetime default CURRENT_TIMESTAMP not null,
  update_time datetime null,
  modifier char(32) null,
  primary key (id, auth_user_id, auth_role_id)
);

create index fk_auth_user_has_auth_role_auth_role1_idx
  on `open-api`.auth_rel_user_role (auth_role_id);

create index fk_auth_user_has_auth_role_auth_user1_idx
  on `open-api`.auth_rel_user_role (auth_user_id);

create table `open-api`.auth_resource
(
  id char(32) not null
    primary key,
  resource_name varchar(64) not null,
  resource_display_name varchar(64) null,
  resource_type tinyint unsigned default 0 not null,
  resource_path varchar(64) not null,
  resource_code varchar(64) not null,
  status tinyint unsigned default 0 not null,
  resource_oprations varchar(32) not null,
  resource_parent_id char(32) null,
  resource_tag varchar(64) null,
  create_time datetime default CURRENT_TIMESTAMP not null,
  update_time datetime null,
  modifier char(32) null,
  constraint resource_code_UNIQUE
    unique (resource_code)
);

create table `open-api`.auth_role
(
  id char(32) not null
    primary key,
  role_name varchar(45) not null,
  role_type tinyint unsigned default 0 not null,
  status tinyint unsigned default 0 not null,
  create_time datetime default CURRENT_TIMESTAMP not null,
  update_time datetime null,
  modifier char(32) null,
  constraint rolename_UNIQUE
    unique (role_name)
);

create table `open-api`.auth_user
(
  id char(32) not null comment 'user system id'
    primary key,
  username varchar(32) not null comment 'user login name',
  password varchar(64) not null comment 'user login password',
  user_type tinyint unsigned default 0 not null comment 'user type. eg. person, company, partner',
  status tinyint unsigned default 0 not null comment 'user status. eg. normal, warn, limit, close',
  create_time datetime default CURRENT_TIMESTAMP not null comment 'create time.',
  update_time datetime null comment 'update time last',
  modifier char(32) null comment 'create/update user id',
  constraint username_UNIQUE
    unique (username)
);

