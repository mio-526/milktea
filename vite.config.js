import { defineConfig,loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
// 增加读取svg
import { svgIconsPlugin } from "@tangllty/vite-plugin-svg";


// https://vitejs.dev/config/
export default defineConfig(({mode})=>{
  const env = loadEnv(mode,process.cwd())
  return{
    resolve: {
      alias: {
        '@': path.resolve(__dirname,'./src')
      }
    },
    server:{
      port: 8080,
      host: true,
      open: true,
      proxy: {
        [env.VITE_BASE_URL]: {
          target: 'http://localhost:8888',
          changeOrigin: true,
          rewrite: (p) => p.replace(new RegExp("^" + env.VITE_BASE_URL), '')
        }
      }
    },
    plugins: [
      svgIconsPlugin({
        pattern: 'src/assets/svg/**/*.svg'
      }),
      vue()
    ],
  }
})
