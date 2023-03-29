PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for CMD
-- ----------------------------
DROP TABLE IF EXISTS "CMD";
CREATE TABLE CMD(
                    ID INTEGER PRIMARY KEY  AUTOINCREMENT    NOT NULL,
                    cmd           CHAR(50) NOT NULL
);

PRAGMA foreign_keys = true;


PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for REPORT
-- ----------------------------
DROP TABLE IF EXISTS "REPORT";
CREATE TABLE "REPORT" (
                          "ID" integer NOT NULL PRIMARY KEY AUTOINCREMENT,
                          "VENDOR" char(8) NOT NULL,
                          "REPORT_ID" char(16) NOT NULL,
                          "REPORT_DATA" TEXT NOT NULL
);

-- ----------------------------
-- Auto increment value for REPORT
-- ----------------------------
UPDATE "main"."sqlite_sequence" SET seq = 3 WHERE name = 'REPORT';

-- ----------------------------
-- Indexes structure for table REPORT
-- ----------------------------
CREATE UNIQUE INDEX "main"."REPORT_ID"
    ON "REPORT" (
                 "REPORT_ID" ASC
        );

PRAGMA foreign_keys = true;