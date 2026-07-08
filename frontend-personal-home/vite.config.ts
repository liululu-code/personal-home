import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv, type ConfigEnv, type UserConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'

export function createViteConfig({ mode }: ConfigEnv): UserConfig {
  const env = loadEnv(mode, process.cwd(), '')
  // 开发服务器代理只在本地运行时生效，目标地址从环境配置读取，便于不同环境独立调整。
  const apiProxyTarget = env.VITE_API_PROXY_TARGET || 'http://localhost:8080'

  return {
    plugins: [
      vue(),
      vueJsx(),
      vueDevTools(),
    ],
    server: {
      proxy: {
        '/api': {
          target: apiProxyTarget,
          changeOrigin: true,
        },
      },
    },
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url)),
      },
    },
  }
}

// https://vite.dev/config/
export default defineConfig(createViteConfig)
