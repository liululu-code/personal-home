import request from '@/utils/request'
import type { AxiosResponse } from 'axios'

export type InputType = 'CREATE_TABLE_SQL'
export type DatabaseType = 'POSTGRESQL'
export type OutputType =
  | 'FAKE_DATA_INSERT_SQL'
  | 'PROJECTION'
  | 'BEAN'
  | 'RES_VO'
  | 'REQ_VO'
export type LocalGenerateContentType =
  | 'ENTITY'
  | 'BEAN'
  | 'REPOSITORY'
  | 'NATIVE_QUERY'
  | 'NATIVE_QUERY_POSTGRESQL'
  | 'SERVICE'
  | 'SERVICE_IMPL'
  | 'CONTROLLER'
  | 'CONVERT'
  | 'REQ_VO'
  | 'LIST_REQ_VO'
  | 'RES_VO'
  | 'PAGE_SUPPORT'

export interface CodeGenerateRequest {
  inputType: InputType
  databaseType: DatabaseType
  outputType: OutputType
  inputContent: string
}

export interface CodeGenerateResponse {
  outputContent: string
}

export interface LocalParseTableRequest {
  databaseType: DatabaseType
  createTableSql: string
}

export interface LocalColumnInfo {
  columnName: string
  quotedColumnName: string
  dataType: string
  length: string | null
  nullable: boolean
  primaryKey: boolean
  defaultValue: string | null
  comment: string
  entityType: string
  entityName: string
}

export interface LocalParseTableResponse {
  schemaName: string | null
  tableName: string
  fullTableName: string
  columns: LocalColumnInfo[]
}

export interface LocalGenerateRequest {
  databaseType: DatabaseType
  createTableSql: string
  packageName: string
  entityClassName: string
  contentTypes: LocalGenerateContentType[]
  entityFields: LocalEntityField[]
  responseClassPackageName: string
  responseClassName: string
  responseSuccessMethodName: string
  pageResultPackageName: string
  paginationPackageName: string
  paginationReqVoPackageName: string
  pageUtilPackageName: string
}

export interface LocalEntityField {
  columnName: string
  entityType: string
  entityName: string
  entityComment: string
}

export interface ApiResponse<T> {
  code: number
  msg: string
  data: T
}

export function generateCode(data: CodeGenerateRequest) {
  return request.post<ApiResponse<CodeGenerateResponse>, ApiResponse<CodeGenerateResponse>>(
    '/api/code-generator/generate',
    data,
  )
}

export function parseLocalTable(data: LocalParseTableRequest) {
  return request.post<ApiResponse<LocalParseTableResponse>, ApiResponse<LocalParseTableResponse>>(
    '/api/code-generator/local/parse-table',
    data,
  )
}

export function generateLocalCode(data: LocalGenerateRequest) {
  return request.post<Blob, AxiosResponse<Blob>>(
    '/api/code-generator/local/generate',
    data,
    {
      rawResponse: true,
      responseType: 'blob',
    },
  )
}
