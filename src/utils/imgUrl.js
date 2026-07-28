export function getImgSrc(path) {
  if (!path) return ""
  if (path.startsWith("http")) {
    // 历史数据可能带 localhost 前缀，公网无法访问，自动转为相对路径
    if (path.includes("localhost") || path.includes("127.0.0.1")) {
      try { path = new URL(path).pathname } catch (e) {}
    } else {
      return path
    }
  }

  let fixedPath = path
  if (!fixedPath.startsWith("/")) {
    fixedPath = "/" + fixedPath
  }

  const base = import.meta.env.DEV ? "http://localhost:8080" : ""
  return base + fixedPath
}

export function getThumbSrc(path) {
  if (!path) return ""
  if (path.startsWith("http")) {
    if (path.includes("localhost") || path.includes("127.0.0.1")) {
      try { path = new URL(path).pathname } catch (e) {}
    } else {
      return path
    }
  }

  let fixedPath = path
  if (!fixedPath.startsWith("/")) {
    fixedPath = "/" + fixedPath
  }

  const base = import.meta.env.DEV ? "http://localhost:8080" : ""
  const name = fixedPath.replace(/.*\//, "").replace(/\.[^.]+$/, "")
  return base + "/uploads/thumb/" + name + ".webp"
}
