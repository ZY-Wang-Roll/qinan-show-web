export function getImgSrc(path) {
  if (!path) return ""
  // 如果已经是完整http链接，直接返回
  if (path.startsWith("http")) return path
  // 开发环境补全后端地址；生产环境空（依靠nginx代理）
  const base = import.meta.env.DEV ? "http://localhost:8080" : ""
  return base + path
}

export function getThumbSrc(path) {
  if (!path) return ""
  if (path.startsWith("http")) return path
  const base = import.meta.env.DEV ? "http://localhost:8080" : ""
  const name = path.replace(/.*\//, "").replace(/\.[^.]+$/, "")
  return base + "/uploads/thumb/" + name + ".webp"
}
