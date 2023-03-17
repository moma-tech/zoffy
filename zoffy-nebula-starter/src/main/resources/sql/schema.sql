# -- ----------------------------
# -- Table structure for UserInfo
# -- ----------------------------
# DROP TABLE IF EXISTS `pk_gl_ods_loan_data_month`;
# CREATE TABLE `pk_gl_ods_loan_data_month`
# (
#     `id`                   bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `create_time`          datetime            NOT NULL DEFAULT current_timestamp() COMMENT '创建日期',
#     `update_time`          datetime            NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日期',
#     `data_month`           varchar(10)         NOT NULL DEFAULT '0000-00-00' COMMENT '数据月份',
#     `third_party_platform` varchar(128)        NOT NULL DEFAULT '' COMMENT '第三方平台',
#     `principal`            varchar(128)        NOT NULL DEFAULT '' COMMENT '放款本金',
#     `pre_service_fee`      varchar(128)        NOT NULL DEFAULT '' COMMENT '应收砍头息-服务费',
#     `pre_gst`              varchar(128)        NOT NULL DEFAULT '' COMMENT '应收砍头息-gst',
#     `service_fee`          varchar(128)        NOT NULL DEFAULT '' COMMENT '应收服务费',
#     `gst`                  varchar(128)        NOT NULL DEFAULT '' COMMENT '应收gst',
#     `interest`             varchar(128)        NOT NULL DEFAULT '' COMMENT '应收利息',
#     `penalty`              varchar(128)        NOT NULL DEFAULT '' COMMENT '应收罚息',
#     `principal_paid`       varchar(128)        NOT NULL DEFAULT '' COMMENT '实收本金',
#     `pre_service_fee_paid` varchar(128)        NOT NULL DEFAULT '' COMMENT '实收砍头息-服务费',
#     `pre_gst_paid`         varchar(128)        NOT NULL DEFAULT '' COMMENT '实收砍头息-gst',
#     `service_fee_paid`     varchar(128)        NOT NULL DEFAULT '' COMMENT '实收服务费',
#     `gst_paid`             varchar(128)        NOT NULL DEFAULT '' COMMENT '实收gst',
#     `interest_paid`        varchar(128)        NOT NULL DEFAULT '' COMMENT '实收利息',
#     `penalty_paid`         varchar(128)        NOT NULL DEFAULT '' COMMENT '实收罚息',
#     `overpaid_amount`      varchar(128)        NOT NULL DEFAULT '' COMMENT '溢缴款',
#     `principal_reduced`    varchar(128)        NOT NULL DEFAULT '' COMMENT '减免本金',
#     `service_fee_reduced`  varchar(128)        NOT NULL DEFAULT '' COMMENT '减免服务费',
#     `gst_reduced`          varchar(128)        NOT NULL DEFAULT '' COMMENT '减免gst',
#     `interest_reduced`     varchar(128)        NOT NULL DEFAULT '' COMMENT '减免利息',
#     `penalty_reduced`      varchar(128)        NOT NULL DEFAULT '' COMMENT '减免罚息',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `uidx_month_platform` (`data_month`, `third_party_platform`) COMMENT '唯一索引-数据月份:第三方平台'
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT ='基础表-LoanData-按月';
#
# DROP TABLE IF EXISTS `pk_gl_ods_cash_in_flow_month`;
# CREATE TABLE `pk_gl_ods_cash_in_flow_month`
# (
#     `id`                   bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `create_time`          datetime            NOT NULL DEFAULT current_timestamp() COMMENT '创建日期',
#     `update_time`          datetime            NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日期',
#     `paid_month`           varchar(10)         NOT NULL DEFAULT '0000-00-00' COMMENT '还款月份',
#     `third_party_platform` varchar(128)        NOT NULL DEFAULT '' COMMENT '第三方平台',
#     `total_amount_paid`    varchar(128)        NOT NULL DEFAULT '' COMMENT '实收总金额',
#     `amount_paid`          varchar(128)        NOT NULL DEFAULT '' COMMENT '实收金额',
#     `unloan_amount_paid`   varchar(128)        NOT NULL DEFAULT '' COMMENT '未放款还款余额',
#     `overpaid_amount`      varchar(128)        NOT NULL DEFAULT '' COMMENT '溢缴款',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `uidx_month_platform` (`paid_month`, `third_party_platform`) COMMENT '唯一索引-还款月份:第三方平台'
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT ='基础表-CashInFlow-按月';
#
# DROP TABLE IF EXISTS `pk_gl_ods_cash_out_flow_month`;
# CREATE TABLE `pk_gl_ods_cash_out_flow_month`
# (
#     `id`                   bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `create_time`          datetime            NOT NULL DEFAULT current_timestamp() COMMENT '创建日期',
#     `update_time`          datetime            NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日期',
#     `loan_month`           varchar(10)         NOT NULL DEFAULT '0000-00-00' COMMENT '放款月份',
#     `third_party_platform` varchar(128)        NOT NULL DEFAULT '' COMMENT '第三方平台',
#     `principal`            varchar(128)        NOT NULL DEFAULT '' COMMENT '放款本金',
#     `unbilled_principal`   varchar(128)        NOT NULL DEFAULT '' COMMENT '放款未生成账单金额',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `uidx_month_platform` (`loan_month`, `third_party_platform`) COMMENT '唯一索引-放款月份:第三方平台'
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT ='基础表-CashOutFlow-按月';
#
# DROP TABLE IF EXISTS `pk_gl_ods_ap_ar_daily`;
# CREATE TABLE `pk_gl_ods_ap_ar_daily`
# (
#     `id`                    bigint(32) unsigned NOT NULL COMMENT '数据id;唯一标识',
#     `create_time`           DATETIME            NOT NULL COMMENT '创建时间;创建时间，应用服务器时间',
#     `update_time`           DATETIME            NOT NULL COMMENT '更新时间;更新时间，同创建时间',
#     `settle_date`           VARCHAR(255)        NOT NULL COMMENT '结算日期',
#     `provider`              VARCHAR(255)        NOT NULL COMMENT '渠道名称',
#     `settle_amount_inflow`  bigint(32)          NOT NULL COMMENT '银行结算流入',
#     `settle_amount_outflow` bigint(32)          NOT NULL COMMENT '银行结算流出',
#     `other_debit`           bigint(32) COMMENT '其它流出（借）',
#     `other_credit`          bigint(32) COMMENT '其它流入（贷）',
#     PRIMARY KEY (id)
# ) COMMENT = '基础表-应收应付ApAr-按天';
# /* 查询索引-结算时间+通道-唯一 */
# CREATE UNIQUE INDEX `index_pk_gl_obs_ap_ar_period_provider` ON `pk_gl_ods_ap_ar_daily` (settle_date, provider);
#
# DROP TABLE IF EXISTS `pk_gl_ret_ap_ar_month`;
# CREATE TABLE `pk_gl_ret_ap_ar_month`
# (
#     `id`                    bigint(32) unsigned NOT NULL COMMENT '数据id;唯一标识',
#     `settle_period`         VARCHAR(255)        NOT NULL COMMENT '周期;统计周期，yyyy-MM',
#     `third_party_platform`  VARCHAR(255)        NOT NULL COMMENT '渠道名称;三方通道名称',
#     `settle_amount_inflow`  VARCHAR(255)        NOT NULL COMMENT '银行结算流入',
#     `settle_amount_outflow` VARCHAR(255)        NOT NULL COMMENT '银行结算流出',
#     `other_debit`           VARCHAR(255)        NOT NULL COMMENT '其它流出（借）',
#     `other_credit`          VARCHAR(255)        NOT NULL COMMENT '其它流入（贷）',
#     `create_time`           DATETIME            NOT NULL COMMENT '创建时间',
#     `update_time`           DATETIME            NOT NULL COMMENT '更新时间',
#     PRIMARY KEY (id)
# ) COMMENT = '结果表-应收应付ApAr-按月';
#
# /*查询索引-结算时间+通道-唯一*/
# CREATE UNIQUE INDEX `index_pk_gl_ret_apar_period_platform` ON `pk_gl_ret_ap_ar_month` (settle_period, third_party_platform);
#
# DROP TABLE IF EXISTS pk_gl_entries_account_code;
# CREATE TABLE pk_gl_entries_account_code
# (
#     `ac_id`        bigint(32)   NOT NULL COMMENT '数据id;数据全局id',
#     `code`         INT          NOT NULL COMMENT '科目编码+排序;同版本下科目编码唯一，可用于排序',
#     `account_code` VARCHAR(255) NOT NULL COMMENT '显示编码;展现用科目编码',
#     `account_name` VARCHAR(255) NOT NULL COMMENT '显示描述;展现用科目名称',
#     `category`     VARCHAR(255) NOT NULL COMMENT '显示分类;展现用科目分类',
#     `drcr`         INT          NOT NULL COMMENT '借方/贷方;借贷标识，0-借方，1-贷方',
#     `source`       VARCHAR(255) NOT NULL COMMENT '数据来源;数据源包括：data/cashin/cashout/apar/entry',
#     `cal_function` VARCHAR(255) NOT NULL COMMENT '计算方式;公式包括：ACC_BY_PERIOD/ACC_BY_PLATFORM/ENTRY_MINUS/ENTRY_SUM/ENTRY_SUM_MINUS/MANUAL',
#     `cal_params`   VARCHAR(255) NOT NULL COMMENT '计算参数;计算使用参数的数据源字段名称',
#     `cal_level`    INT          NOT NULL DEFAULT 99 COMMENT '计算优先级;计算顺序，自然数从小到大，99-基于其他科目计算',
#     `version`      VARCHAR(255) NOT NULL COMMENT '版本;支持历史数据展示',
#     `enable`       INT          NOT NULL DEFAULT 1 COMMENT '数据有效性;0-无效，1-有效',
#     `create_time`  DATETIME     NOT NULL COMMENT '创建时间;创建时间，应用服务器时间',
#     `update_time`  DATETIME     NOT NULL COMMENT '更新时间;更新时间，同创建时间',
#     PRIMARY KEY (ac_id)
# ) COMMENT = '巴基斯坦凭证科目计算模版表';
# /*查询索引-科目编码+版本-唯一*/
# CREATE UNIQUE INDEX index_pk_gl_entry_ac_version_code ON pk_gl_entries_account_code (code, version);
# /*排序索引-科目编码*/
# CREATE INDEX index_pk_gl_entry_ac_code_sort ON pk_gl_entries_account_code (code);
#
# DROP TABLE IF EXISTS pk_gl_entries;
# CREATE TABLE pk_gl_entries
# (
#     `entry_id`        bigint(32)   NOT NULL COMMENT '记录id;唯一标识',
#     `entry_period`    VARCHAR(255) NOT NULL COMMENT '周期;计算的凭证周期',
#     `account_code_id` bigint(32)   NOT NULL COMMENT '所属凭证科目id;关联巴基斯坦凭证科目模版表',
#     `dr`              VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '借方金额;借方金额计算结果',
#     `cr`              VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '贷方金额;贷方金额计算结果',
#     `balance`         VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '结余金额;借方金额 - 贷方金额，可为负',
#     `sort`            INT          NOT NULL COMMENT '排序号;展示用排序号',
#     `enable`          INT          NOT NULL DEFAULT 1 COMMENT '数据有效性;0-无效，1-有效',
#     `create_time`     DATETIME     NOT NULL COMMENT '创建时间;创建时间，应用服务器时间',
#     `update_time`     DATETIME     NOT NULL COMMENT '更新时间;更新时间，同创建时间',
#     `create_user`     VARCHAR(255) NOT NULL DEFAULT 'Scheduler' COMMENT '创建用户;创建用户，默认Scheduler',
#     `update_user`     VARCHAR(255) NOT NULL DEFAULT 'Scheduler' COMMENT '更新用户;更新用户，默认Scheduler',
#     PRIMARY KEY (entry_id)
# ) COMMENT = '巴基斯坦凭证记录表';
# /*查询索引-凭证周期+科目id-唯一*/
# CREATE UNIQUE INDEX index_pk_lg_entry_period_acid ON pk_gl_entries (entry_period, account_code_id);
# /*查询索引-凭证周期*/
# CREATE INDEX index_pk_lg_entry_period ON pk_gl_entries (entry_period);