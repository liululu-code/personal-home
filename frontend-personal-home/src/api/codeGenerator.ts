import request from '@/utils/request'

export type InputType = 'CREATE_TABLE_SQL'
export type DatabaseType = 'POSTGRESQL'
export type OutputType =
  | 'FAKE_DATA_INSERT_SQL'
  | 'PROJECTION'
  | 'BEAN'
  | 'RES_VO'
  | 'REQ_VO'

export interface CodeGenerateRequest {
  inputType: InputType
  databaseType: DatabaseType
  outputType: OutputType
  inputContent: string
}

export interface CodeGenerateResponse {
  outputContent: string
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
