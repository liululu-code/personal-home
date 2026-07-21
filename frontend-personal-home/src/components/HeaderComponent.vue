<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="home-page">个人主页</el-menu-item>
    <el-menu-item index="anime-download">动漫下载</el-menu-item>
    <el-menu-item index="comic-read">漫画阅读</el-menu-item>
    <el-menu-item index="code-generator">代码生成</el-menu-item>
    <el-menu-item index="website-guide">网址导航</el-menu-item>
    <el-sub-menu index="learn-demo">
      <template #title>学习Demo</template>
      <el-menu-item index="qr-demo">验证码</el-menu-item>
      <el-menu-item index="blog-demo">第二个</el-menu-item>
    </el-sub-menu>

    <el-menu-item index="contact-me">联系我</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const activeIndex = computed(() => String(route.name ?? 'home-page'))

// 菜单 index 使用路由 name，避免路由层级调整时同步维护多份路径。
const handleSelect = (key: string) => {
  console.log(key);

  if (!router.hasRoute(key)) {
    console.log("没有路由：" + key);
    return
  }
  router.push({ name: key })
}
</script>

<style scoped lang="scss">
.el-menu--horizontal > .el-menu-item:nth-last-child(2) {
  margin-right: auto;
}
</style>
