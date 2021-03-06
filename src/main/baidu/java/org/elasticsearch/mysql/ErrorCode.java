/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elasticsearch.mysql;

import java.util.MissingFormatArgumentException;

// Error code used to indicate what error happened.
public enum ErrorCode {
    // Try our best to compatible with MySQL's
    ERR_CANT_CREATE_TABLE(1005, new byte[] { 'H', 'Y', '0', '0', '0' }, "Can't create table '%s' (errno: %d)"),
    ERR_DB_CREATE_EXISTS(1007, new byte[] {'H', 'Y', '0', '0', '0'}, "Can't create database '%s'; database exists"),
    ERR_DB_DROP_EXISTS(1008, new byte[] {'H', 'Y', '0', '0', '0'}, "Can't drop database '%s'; database doesn't exist"),
    ERR_IP_ACCESS_DENIED(1043, new byte[] {'2', '8', '0', '0', '0'}, "Access denied for ip '%s'"),
    ERR_DB_ACCESS_DENIED(1044, new byte[] {'4', '2', '0', '0', '0'}, "Access denied for user '%s' to database '%s'"),
    ERR_ACCESS_DENIED_ERROR(1045, new byte[] {'2', '8', '0', '0', '0'},
            "Access denied for user '%s' (using password: %s)"),
    ERR_NO_DB_ERROR(1046, new byte[] {'3', 'D', '0', '0', '0'}, "No database selected"),
    ERR_UNKNOWN_COM_ERROR(1047, new byte[] {'0', '8', 'S', '0', '1'}, "Unknown command"),
    ERR_BAD_DB_ERROR(1049, new byte[] {'4', '2', '0', '0', '0'}, "Unknown database '%s'"),
    ERR_TABLE_EXISTS_ERROR(1050, new byte[] {'4', '2', 'S', '0', '1'}, "Table '%s' already exists"),
    ERR_BAD_TABLE_ERROR(1051, new byte[] {'4', '2', 'S', '0', '2'}, "Unknown table '%s'"),
    ERR_NON_UNIQ_ERROR(1052, new byte[] {'2', '3', '0', '0', '0'}, "Column '%s' in is ambiguous"),
    ERR_BAD_FIELD_ERROR(1054, new byte[] {'4', '2', 'S', '2', '2'}, "Unknown column '%s' in '%s'"),
    ERR_WRONG_VALUE_COUNT(1058, new byte[] {'2', '1', 'S', '0', '1'}, "Column count doesn't match value count"),
    ERR_DUP_FIELDNAME(1060, new byte[] {'4', '2', 'S', '2', '1'}, "Duplicate column name '%s'"),
    ERR_NONUNIQ_TABLE(1066, new byte[] {'4', '2', '0', '0', '0'}, "Not unique table/alias: '%s'"),
    ERR_NO_SUCH_THREAD(1094, new byte[] {'H', 'Y', '0', '0', '0'}, "Unknown thread id: %d"),
    ERR_KILL_DENIED_ERROR(1095, new byte[] {'H', 'Y', '0', '0', '0'}, "You are not owner of thread %d"),
    ERR_NO_TABLES_USED(1096, new byte[] {'H', 'Y', '0', '0', '0'}, "No tables used"),
    ERR_WRONG_DB_NAME(1102, new byte[] {'4', '2', '0', '0', '0'}, "Incorrect database name '%s'"),
    ERR_WRONG_TABLE_NAME(1104, new byte[] {'4', '2', '0', '0', '0'}, "Incorrect table name '%s'"),
    ERR_UNKNOWN_ERROR(1105, new byte[] {'H', 'Y', '0', '0', '0'}, "Unknown error"),
    ERR_FIELD_SPECIFIED_TWICE(1110, new byte[] {'4', '2', '0', '0', '0'}, "Column '%s' specified twice"),
    ERR_INVALID_GROUP_FUNC_USE(1111, new byte[] {'H', 'Y', '0', '0', '0'}, "Invalid use of group function"),
    ERR_TABLE_MUST_HAVE_COLUMNS(1113, new byte[] {'4', '2', '0', '0', '0'}, "A table must have at least 1 column"),
    ERR_UNKNOWN_CHARACTER_SET(1115, new byte[] {'4', '2', '0', '0', '0'}, "Unknown character set: '%s'"),
    ERR_PASSWORD_NOT_ALLOWED(1132, new byte[] {'4', '2', '0', '0', '0'},
            "You must have privileges to "
                    + "update tables in the mysql database to be able to change passwords for others"),
    ERR_WRONG_COLUMN_NAME(1166, new byte[] {'4', '2', '0', '0', '0'}, "Incorrect column name '%s'"),
    ERR_UNKNOWN_SYSTEM_VARIABLE(1193, new byte[] {'H', 'Y', '0', '0', '0'}, "Unknown system variable '%s'"),
    ERR_TOO_MANY_USER_CONNECTIONS(1203, new byte[] {'4', '2', '0', '0', '0'},
            "User %s already has more than 'max_user_connections' active connections"),
    ERR_NO_PERMISSION_TO_CREATE_USER(1211, new byte[] {'4', '2', '0', '0', '0'},
            "'%s' is not allowed to create new users"),
    ERR_SPECIFIC_ACCESS_DENIED_ERROR(1227, new byte[] {'4', '2', '0', '0', '0'},
            "Access denied; you need (at least one of) the %s privilege(s) for this operation"),
    ERR_LOCAL_VARIABLE(1228, new byte[] {'H', 'Y', '0', '0', '0'},
            "Variable '%s' is a SESSION variable and can't be used with SET GLOBAL"),
    ERR_GLOBAL_VARIABLE(1229, new byte[] {'H', 'Y', '0', '0', '0'},
            "Variable '%s' is a GLOBAL variable and should be set with SET GLOBAL"),
    ERR_NO_DEFAULT(1230, new byte[] {'4', '2', '0', '0', '0'}, "Variable '%s' doesn't have a default value"),
    ERR_WRONG_VALUE_FOR_VAR(1231, new byte[] {'4', '2', '0', '0', '0'},
            "Variable '%s' can't be set to the value of '%s'"),
    ERR_WRONG_TYPE_FOR_VAR(1232, new byte[] {'4', '2', '0', '0', '0'}, "Incorrect argument type to variable '%s'"),
    ERR_DERIVED_MUST_HAVE_ALIAS(1248, new byte[] {'4', '2', '0', '0', '0'},
            "Every derived table must have its own alias"),
    ERR_NOT_SUPPORTED_AUTH_MODE(1251, new byte[] {'0', '8', '0', '0', '4'},
            "Client does not support authentication protocol requested by server; consider upgrading MySQL client"),
    ERR_UNKNOWN_STORAGE_ENGINE(1286, new byte[] {'4', '2', '0', '0', '0'}, "Unknown storage engine '%s'"),
    ERR_WRONG_OBJECT(1347, new byte[] {'H', 'Y', '0', '0', '0'}, "'%s'.'%s' is not '%s'"),
    ERR_VIEW_WRONG_LIST(1353, new byte[] {'H', 'Y', '0', '0', '0'},
            "View's SELECT and view's field list have different column counts"),
    ERR_NO_DEFAULT_FOR_FIELD(1364, new byte[] {'H', 'Y', '0', '0', '0'}, "Field '%s' doesn't have a default value"),
    ERR_PASSWD_LENGTH(1372, new byte[] {'H', 'Y', '0', '0', '0'},
            "Password hash should be a %d-digit hexadecimal number"),
    ERR_CANNOT_USER(1396, new byte[] {'H', 'Y', '0', '0', '0'}, "Operation %s failed for %s"),
    ERR_NON_INSERTABLE_TABLE(1471, new byte[] {'H', 'Y', '0', '0', '0'},
            "The target table %s of the %s is not insertable-into"),
    ERR_DROP_PARTITION_NON_EXISTENT(1507, new byte[] { 'H', 'Y', '0', '0', '0' }, "Error in list of partitions to %s"),
    ERR_DROP_LAST_PARTITION(1508, new byte[] { 'H', 'Y', '0', '0', '0' },
            "Cannot remove all partitions, use DROP TABLE instead"),
    ERR_SAME_NAME_PARTITION(1517, new byte[] { 'H', 'Y', '0', '0', '0' }, "Duplicate partition name %s"),
    ERR_WRONG_PARTITION_NAME(1567, new byte[] {'H', 'Y', '0', '0', '0'}, "Incorrect partition name '%s'"),
    ERR_VARIABLE_IS_READONLY(1621, new byte[] {'H', 'Y', '0', '0', '0'}, "Variable '%s' is a read only variable"),
    ERR_UNKNOWN_PARTITION(1735, new byte[] {'H', 'Y', '0', '0', '0'}, "Unknown partition '%s' in table '%s'"),
    ERR_PARTITION_CLAUSE_ON_NONPARTITIONED(1747, new byte[] {'H', 'Y', '0', '0', '0'},
            "PARTITION () clause on non partitioned table"),
    ERR_NO_SUCH_PARTITION(1749, new byte[] {'H', 'Y', '0', '0', '0'}, "partition '%s' doesn't exist"),
    // Following is Palo's error code, which start from 5000
    ERR_NOT_OLAP_TABLE(5000, new byte[] {'H', 'Y', '0', '0', '0'}, "Table '%s' is not a OLAP table"),
    ERR_WRONG_PROC_PATH(5001, new byte[] {'H', 'Y', '0', '0', '0'}, "Proc path '%s' doesn't exist"),
    ERR_COL_NOT_MENTIONED(5002, new byte[] {'H', 'Y', '0', '0', '0'},
            "'%s' must be explicitly mentioned in column permutation"),
    ERR_OLAP_KEY_MUST_BEFORE_VALUE(5003, new byte[] { 'H', 'Y', '0', '0', '0' },
            "Key column must before value column"),
    ERR_OLAP_MUST_HAVE_KEYS(5004, new byte[] {'H', 'Y', '0', '0', '0'},
            "OLAP table must have at least 1 key column"),
    ERR_UNKNOWN_CLUSTER_ID(5005, new byte[] {'H', 'Y', '0', '0', '0'}, "Unknown cluster id '%s'"),
    ERR_UNKNOWN_PLAN_HINT(5006, new byte[] {'H', 'Y', '0', '0', '0'}, "Unknown plan hint '%s'"),
    ERR_PLAN_HINT_CONFILT(5007, new byte[] {'H', 'Y', '0', '0', '0'}, "Conflict plan hint '%s'"),
    ERR_INSERT_HINT_NOT_SUPPORT(5008, new byte[] {'H', 'Y', '0', '0', '0'},
            "INSERT hints are only supported for partitioned table"),
    ERR_PARTITION_CLAUSE_NO_ALLOWED(5009, new byte[] {'H', 'Y', '0', '0', '0'},
            "PARTITION clause is not valid for INSERT into unpartitioned table"),
    ERR_COL_NUMBER_NOT_MATCH(5010, new byte[] {'H', 'Y', '0', '0', '0'},
            "Number of columns don't equal number of SELECT statement's select list"),
    ERR_UNRESOLVED_TABLE_REF(5011, new byte[] {'H', 'Y', '0', '0', '0'},
            "Unresolved table reference '%s'"),
    ERR_BAD_NUMBER(5012, new byte[] {'H', 'Y', '0', '0', '0'}, "'%s' is not a number"),
    ERR_BAD_TIMEUNIT(5013, new byte[] { 'H', 'Y', '0', '0', '0' }, "Unsupported time unit '%s'"),
    ERR_BAD_TABLE_STATE(5014, new byte[] { 'H', 'Y', '0', '0', '0' }, "Table state is not NORMAL: '%s'"),
    ERR_BAD_PARTITION_STATE(5015, new byte[] { 'H', 'Y', '0', '0', '0' }, "Partition state is not NORMAL: '%s':'%s'"),
    ERR_PARTITION_HAS_LOADING_JOBS(5016, new byte[] { 'H', 'Y', '0', '0', '0' }, "Partition has loading jobs: '%s'"),
    ERR_NOT_KEY_COLUMN(5017, new byte[] { 'H', 'Y', '0', '0', '0' }, "Column is not a key column: '%s'"),
    ERR_INVALID_VALUE(5018, new byte[] { 'H', 'Y', '0', '0', '0' }, "Invalid value format: '%s'"),
    ERR_REPLICA_NOT_CATCH_UP_WITH_VERSION(5019, new byte[] { 'H', 'Y', '0', '0', '0' },
            "Replica does not catch up with version: '%s':'%s'"),
    ERR_BACKEND_OFFLINE(5021, new byte[] { 'H', 'Y', '0', '0', '0' }, "Backend is offline: '%s'"),
    ERR_BAD_PARTS_IN_UNPARTITION_TABLE(5022, new byte[] { 'H', 'Y', '0', '0', '0' },
            "Number of partitions in unpartitioned table is not 1"),
    ERR_NO_ALTER_OPERATION(5023, new byte[] { 'H', 'Y', '0', '0', '0' },
            "No operation in alter statement"),
    ERR_EXECUTE_TIMEOUT(5024, new byte[] { 'H', 'Y', '0', '0', '0' }, "Execute timeout"),
    ERR_FAILED_WHEN_INSERT(5025, new byte[] { 'H', 'Y', '0', '0', '0' }, "Failed when INSERT execute"),
    ERR_UNSUPPORTED_TYPE_IN_CTAS(5026, new byte[] { 'H', 'Y', '0', '0', '0' },
            "Unsupported type '%s' in create table as select statement"),
    ERR_MISSING_PARAM(5027, new byte[] { 'H', 'Y', '0', '0', '0' }, "Missing param: %s ");

    ErrorCode(int code, byte[] sqlState, String errorMsg) {
        this.code = code;
        this.sqlState = sqlState;
        this.errorMsg = errorMsg;
    }

    // This is error code
    private int code;
    // This sql state is compatible with ANSI SQL
    private byte[] sqlState;
    // Error message format
    private String errorMsg;

    public int getCode() {
        return code;
    }

    public byte[] getSqlState() {
        return sqlState;
    }

    public String formatErrorMsg(Object... args) {
        try {
            return String.format(errorMsg, args);
        } catch (MissingFormatArgumentException e) {
            return errorMsg;
        }
    }
}
