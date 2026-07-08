// request.ts
import axios, { type InternalAxiosRequestConfig } from 'axios'

declare module 'axios' {
  export interface AxiosRequestConfig {
    rawResponse?: boolean
  }

  export interface InternalAxiosRequestConfig {
    rawResponse?: boolean
  }
}

const apiBaseURL = import.meta.env.VITE_API_BASE_URL

const request = axios.create({
  baseURL: apiBaseURL,
  timeout: 5000,
})

function normalizeBaseURL(config: InternalAxiosRequestConfig) {
  const requestUrl = config.url || ''
  // 兼容历史代码中已经带有 /api 前缀的接口路径，避免 baseURL 再次拼接导致 /api/api。
  if (apiBaseURL && requestUrl.startsWith(`${apiBaseURL}/`)) {
    config.baseURL = ''
  }
}

request.interceptors.request.use(
  (config) => {
    normalizeBaseURL(config)
    console.log('request ' + config.url)
    return config
  },
  (config) => {
    console.log('request ' + config.url + 'error!')
    return config
  },
)
request.interceptors.response.use((response) => {
  if (response.config.rawResponse) {
    return response
  }
  return response.data
})
export default request
