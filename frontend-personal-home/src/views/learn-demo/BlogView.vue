<template>
  <!-- 页面 -->
  <main class="container">
    <!-- 卡片 -->
    <article class="blog-card">
      <!-- 图片 -->
      <el-image
        class="blog-card__image"
        src="/demo/illustration-article.svg"
        fit="cover"
        :lazy="true"
        alt="封面"
      ></el-image>
      <!-- 标签 -->
      <div class="blog-card__tag">learning</div>
      <!-- 发布日期 -->
      <p class="blog-card__date">Published 21 Dec 2023</p>
      <!-- 标题 -->
      <h1 class="blog-card__title">HTML & CSS foundations</h1>
      <!-- 内容 -->
      <p class="blog-card__description">
        These languages are the backbone of every website, defining structure, content, and presentation
      </p>
      <!-- 作者 -->
      <div class="blog-card__author">
        <el-image class="author-avatar" src="/demo/image-avatar.webp" fit="cover"></el-image>
        <span class="author-name">Liu Lulu</span>
      </div>
    </article>
  </main>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 24px;
  background-color: rgb(245, 208, 78);
  font-family: Arial, Helvetica, sans-serif;

  .blog-card {
    width: min(100%, 400px);
    padding: 16px;
    border-radius: 20px;
    background-color: #fff;
    border: 1px solid #111;
    box-shadow: 8px 8px 0 #111;

    .blog-card__image {
      width: 100%;
      border-radius: 10px;
    }

    .blog-card__tag {
      display: inline-flex;
      justify-content: center;
      align-items: center;
      padding: 0 12px;
      min-height: 30px;
      margin: 16px 0px;
      border-radius: 4px;
      background-color: rgb(245, 208, 78);
      font-weight: 700;
    }

    .blog-card__date {
      font-weight: 700;
    }

    .blog-card__title {
      margin: 14px auto;
      font-size: 22px;
      font-weight: 900;
    }

    .blog-card__description {
      color: #3b4049;
      font-size: 16px;
      font-weight: 400;
    }

    .blog-card__author {
      display: flex;
      align-items: center;
      margin: 16px 0;
      gap: 8px;

      .author-avatar {
        width: 32px;
        aspect-ratio: 1;
      }

      .author-name {
        font-size: 18px;
        font-weight: 700;
      }
    }
  }
}
</style>
