// request.ts
import axios from 'axios'

const request = axios.create({
  timeout: 5000,
})
request.interceptors.request.use(
  (config) => {
    console.log('request ' + config.url)
    return config
  },
  (config) => {
    console.log('request ' + config.url + 'error!')
    return config
  },
)
request.interceptors.response.use((response) => {
  return response.data
})
export default request
