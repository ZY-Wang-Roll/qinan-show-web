export function getImgSrc(path) {
  if (!path) return ""
  if (path.startsWith("http")) return path

  // 自动修复缺失前置斜杠的历史图片
  let fixedPath = path
  if (!fixedPath.startsWith("/")) {
    fixedPath = "/" + fixedPath
  }

  const base = import.meta.env.DEV ? "http://localhost:8080" : ""
  return base + fixedPath
}

export function getThumbSrc(path) {
  if (!path) return ""
  if (path.startsWith("http")) return path

  let fixedPath = path
  if (!fixedPath.startsWith("/")) {
    fixedPath = "/" + fixedPath
  }

  const base = import.meta.env.DEV ? "http://localhost:8080" : ""
  const name = fixedPath.replace(/.*\//, "").replace(/\.[^.]+$/, "")
  return base + "/uploads/thumb/" + name + ".webp"
}
