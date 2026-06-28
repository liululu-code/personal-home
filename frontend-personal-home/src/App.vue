<script setup lang="ts">
import { RouterView } from 'vue-router';
import FooterComponent from './components/FooterComponent.vue';
import HeaderComponent from './components/HeaderComponent.vue';
</script>

<template>
  <el-container class="page-container">
    <el-header>
      <HeaderComponent />
    </el-header>
    <el-main>
      <RouterView />
    </el-main>
    <el-footer>
      <FooterComponent />
    </el-footer>
  </el-container>
</template>

<style scoped lang="scss">
.page-container {
  height: 100vh;
}
</style>
