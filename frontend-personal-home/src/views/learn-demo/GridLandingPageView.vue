<template>
  <div class="page">
    <header class="header">
      <div class="header__left">
        <p class="header__left__icon"></p>
        <p class="header__left__title">Bridge Collection</p>
      </div>
      <el-image class="header__right__icon" src="/demo/grid-landing-page/icon-menu.svg" />
    </header>

    <main class="content">
      <section class="content__intro">
        <h1>A classroom for every child.</h1>
        <p>We fund the schools, train the teachers, and measure what works — so every child we reach today becomes a graduate tomorrow.</p>
      </section>

      <section class="content__stats">
        <div class="content__stats__card" v-for="cardData in cardList" :key=cardData.iconUrl>
          <div class="content__stats__card__upper">
            <el-image :src=cardData.iconUrl style="width: 20px; height: 20px;"></el-image>
            <p class="content__stats__card__upper__number">{{ cardData.number }}</p>
          </div>
          <div class="content__stats__card__bottom">
            <p class="content__stats__card__bottom__title">{{cardData.cardTitle}}</p>
            <p class="content__stats__card__bottom__desc">{{ cardData.cardTitle }}</p>
          </div>
        </div>
      </section>
    </main>

    <footer class="footer">
      <div class="foot__left">@ 2026 Bridge Collection</div>
      <div class="foot__left">Registered charity 12345678</div>
    </footer>
  </div>
</template>

<script setup lang="ts">
const cardList = [
  {
    iconUrl: "/demo/grid-landing-page/icon-sparkle.svg",
    number: "2.4M",
    cardTitle: "Students reached",
    cardDesc: "Across 31 countries since 2011."
  },
  {
    iconUrl: "/demo/grid-landing-page/icon-plus.svg",
    number: "1,284",
    cardTitle: "Schools partnered",
    cardDesc: "In 14 countries, from Kenya to Guatemala."
  },
  {
    iconUrl: "/demo/grid-landing-page/icon-arrow-right.svg",
    number: "38K",
    cardTitle: "Teachers trained",
    cardDesc: "Equipped with modern tools and methodology."
  },
  {
    iconUrl: "/demo/grid-landing-page/icon-trending-up.svg",
    number: "3.1×",
    cardTitle: "Graduation lift",
    cardDesc: "Partner schools outperform national averages 3x."
  }
];

</script>

<style scoped lang="scss">

.page {

  margin: auto;
  width: 1040px;
  height: 580px;
  display: grid;
  grid-template-rows: 70px 1fr 70px;
  grid-template-columns: 1fr;

  background-color: #2854fd;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 40px;
    border-bottom: 1px solid rgba(#fff, 0.15);

    &__left {
      display: flex;
      align-items: center;
      gap: 10px;

      &__icon {
        width: 10px;
        height: 10px;
        border-radius: 50%;
        background-color: #fff;
      }

      &__title {
        font-size: 14px;
        font-weight: 400;
        color: #fff;
      }

    }

    &__right {
      display: flex;
      align-items: center;
    }
  }

  .content {
    display: grid;
    grid-template-columns: 1fr 1.2fr;
    border-bottom: 1px solid rgba(#fff, 0.15);

    &__intro {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-content: flex-start;
      padding: 0px 13px 0px 20px;
      transform: translate(0, -26px);


      h1 {
        font-size: 40px;
        color: #fcfdfc;
      }

      p {
        font-size: 12px;
        color: #d1d4da
      }
    }

    &__stats {
      display: grid;
      grid-template-columns: repeat(2, 1fr);
      grid-template-rows: repeat(2, 1fr);


      &__card {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        border-left: 1px solid rgba(#fff, 0.15);
        border-bottom: 1px solid rgba(#fff, 0.15);
        padding: 20px;

        &__upper {
          display: flex;
          justify-content: space-between;
          align-content: flex-start;

          &__number {
            color: #fcfbfc;
            font-size: 24px;
          }
        }

        &__bottom {
          &__title {
            color: #9eb8f6;
            font-size: 14px;
          }
          &__desc {
            color: #869cd1;
            font-size: 12px;
          }
        }
      }
    }

  }

  .footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 40px;
    color: #fff;
    font-size: 14px;
  }


}


</style>
