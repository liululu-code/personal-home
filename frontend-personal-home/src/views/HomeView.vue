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

<script setup lang="ts">
import FooterComponent from '@/components/FooterComponent.vue';
import HeaderComponent from '@/components/HeaderComponent.vue';
import { RouterView } from 'vue-router';


</script>

<style scoped lang="scss">
.page-container {
  min-height: 100dvh;

  > .el-main {
    display: flex;
  }
}
</style>
