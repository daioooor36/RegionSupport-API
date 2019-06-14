## DB Object 생성

### 1. RG_INFORM 테이블 생성

```
CREATE TABLE `rg_inform` (
  `region_cd` varchar(6) NOT NULL COMMENT '지자체 코드',
  `region_nm` varchar(50) NOT NULL COMMENT '지자체 명',
  PRIMARY KEY (`region_cd`),
  UNIQUE KEY `region_cd_UNIQUE` (`region_cd`),
  UNIQUE KEY `region_nm_UNIQUE` (`region_nm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='지원 지자체 정보'
```

### 2. RG_APPLY_DATA 테이블 생성

```
CREATE TABLE `rg_apply_data` (
  `id` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ID',
  `region_cd` varchar(6) CHARACTER SET utf8 NOT NULL COMMENT '지자체 코드',
  `target` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '지원대상',
  `usage` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '용도',
  `limit` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '지원한도',
  `rate` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `institute` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '추천기관',
  `mgmt` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '관리점',
  `reception` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '취급점',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  `upd_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자'
  PRIMARY KEY (`id`),
  KEY `FK_RG_APPLY_DATA_RG_INFORM` (`region_cd`),
  CONSTRAINT `FK_RG_APPLY_DATA_RG_INFORM` FOREIGN KEY (`region_cd`) REFERENCES `rg_inform` (`region_cd`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='지자체 지원정보'
```

■
