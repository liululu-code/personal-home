<template>
  <main class="container">
    <article class="social-card">
      <el-image
        class="social-card__avatar"
        src="/demo/girl.png"
        fit="cover"
        alt="Jessica Randall的头像"
      ></el-image>
      <h1 class="social-card__name">Jessica Randall</h1>
      <p class="social-card__position">London, United Kingdom</p>
      <p class="social-card__personal-introduction">"Front-end developer and avid reader."</p>
      <ul class="social-card__link-box">
        <li class="social-card__link-item" v-for="value in linkList" :key="value">
          <a class="social-card__link" href="https://www.baidu.com">{{ value }}</a>
        </li>
      </ul>
    </article>
  </main>
</template>

<script setup lang="ts">
const linkList = ['GitHub', 'Frontend Mentor', 'LinkedIn', 'Twitter', 'Instagram']
</script>

<style scoped lang="scss">
.container {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 24px;
  background-color: rgb(20, 20, 20);

  .social-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    max-width: 360px;
    border-radius: 10px;
    background-color: rgb(31, 31, 31);
    color: white;

    .social-card__avatar {
      width: 80px;
      aspect-ratio: 1;
      border-radius: 50%;
      margin: 40px auto 30px;
    }

    .social-card__name {
      font-size: 22px;
      font-weight: 400;
      color: rgb(201, 201, 201);
    }

    .social-card__position {
      color: rgb(191, 218, 115);
      font-size: 14px;
      font-weight: 500;
      margin-bottom: 20px;
    }

    .social-card__personal-introduction {
      color: rgb(169, 169, 169);
    }

    .social-card__link-box {
      display: flex;
      flex-direction: column;
      gap: 16px;
      margin: 20px auto 30px;
      width: 80%;


        .social-card__link {
          display: flex;
          justify-content: center;
          align-items: center;
          font-size: 18px;
          min-height: 44px;
          border-radius: 6px;
          color: rgb(201, 201, 201);
          background-color: rgb(51, 51, 51);
        }


      .social-card__link:hover {
        color: rgb(27, 54, 0);
        background-color: rgb(196, 248, 42);
      }
    }
  }
}
</style>
