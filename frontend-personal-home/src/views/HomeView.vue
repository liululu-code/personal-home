<template>
  <el-container class="page-container">
    <el-header>
      <HeaderComponent />
    </el-header>
    <el-main>主页面</el-main>
    <el-footer>
      <FooterComponent />
    </el-footer>
  </el-container>
</template>

<script setup lang="ts">
import FooterComponent from '@/components/FooterComponent.vue';
import HeaderComponent from '@/components/HeaderComponent.vue';
</script>

<style scoped lang="scss">
.page-container {
  height: 100vh;
}
</style>
