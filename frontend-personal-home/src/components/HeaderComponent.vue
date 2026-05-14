<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="home-page">个人主页</el-menu-item>
    <el-sub-menu index="project">
      <template #title>项目</template>
      <el-menu-item index="website-guide">网址导航</el-menu-item>
      <el-menu-item index="anime-download">动漫下载</el-menu-item>
      <el-menu-item index="comic">漫画阅读</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="contact-me" >联系我</el-menu-item>
  </el-menu>


</template>

<script lang="ts" setup>
import { ref } from 'vue';

const activeIndex = ref('1')

// todo:点击跳转
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<style scoped lang="scss">
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>
