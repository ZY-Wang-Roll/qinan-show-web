// 环境区分：本地开发用完整地址，线上用相对路径（Nginx 代理）
const BASE_API = import.meta.env.DEV ? 'http://localhost:8080' : ''

/**
 * 统一图片路径处理
 * @param {string} path - 图片路径，如 '/uploads/xxx.jpg'
 * @returns {string} 完整可访问的图片 URL
 */
export function getImgSrc(path) {
  if (!path) return ''
  if (path.startsWith('http')) return path
  return BASE_API + path
}

/**
 * 获取 WebP 缩略图路径（页面展示用，非 lightbox）
 * @param {string} path - 原图路径，如 '/uploads/xxx.jpg'
 * @returns {string} 缩略图 URL
 */
export function getThumbSrc(path) {
  if (!path) return ''
  if (path.startsWith('http')) return path
  const name = path.replace(/.*\//, '').replace(/\.[^.]+$/, '')
  return BASE_API + '/uploads/thumb/' + name + '.webp'
}
