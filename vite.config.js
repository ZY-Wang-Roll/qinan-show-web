import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  
  server: {
    allowedHosts: true,
    host: '0.0.0.0',
    port: 3000,
    // 开发代理：/api 和 /uploads 转发到后端 8080
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      '/uploads': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
  base: './',
  // 生产构建资源使用相对路径
  
})
