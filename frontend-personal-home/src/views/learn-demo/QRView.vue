<template>
  <main class="home-page">
    <section class="qr-card">
      <el-image class="qr-card__image" src="/demo/image-qr-code.png" fit="cover" />
      <h1 class="qr-card__title">Improve your front-end skills by building projects</h1>
      <p class="qr-card__description">
        Scan the QR code to visit Frontend Mentor and take your coding skills to the next level
      </p>
    </section>
  </main>
</template>

<script setup lang="ts">

</script>

<style scoped lang="scss">
.home-page {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 24px;
  background-color: #d5e1ef;
  font-family:
    Arial,
    Helvetica,
    sans-serif;
}

.qr-card {
  width: min(100%, 320px);
  padding: 16px 16px 40px;
  border-radius: 20px;
  background-color: #ffffff;
  box-shadow: 0 18px 34px rgb(104 119 141 / 18%);
  text-align: center;
}

.qr-card__image {
  width: 100%;
  aspect-ratio: 1;
  overflow: hidden;
  border-radius: 10px;
}

.qr-card__title {
  margin: 24px auto 14px;
  max-width: 288px;
  color: #1f314f;
  font-size: 22px;
  font-weight: 700;
  line-height: 1.28;
}

.qr-card__description {
  max-width: 260px;
  margin: 0 auto;
  color: #7d889e;
  font-size: 15px;
  line-height: 1.25;
}
</style>
