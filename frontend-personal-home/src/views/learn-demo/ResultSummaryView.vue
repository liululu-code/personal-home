<template>
  <main class="result-summary-container">
    <article class="result-summary-card">
      <div class="result-card">
        <h1 class="result-card__title">Your Result</h1>
        <div class="result-card__score">
          <span class="result-card__score-value">76</span>
          <span class="result-card__score-max">of 100</span>
        </div>
        <h1 class="result-card__evaluate">Great</h1>
        <p class="result-card__description">You scored higher than 65% of the people who have taken these tests.</p>
      </div>
      <div class="summary-card">
        <h1 class="summary-card__title">Summary</h1>
        <ul class="summary-card__list">
          <li
            v-for="item in summaryList"
            :key="item.title"
            class="summary-card__item"
            :class="`summary-card__item--${item.tone}`"
          >
            <div class="left">
              <el-image :src="item.iconPath" class="summary-card__item-icon" />
              <h2 class="summary-card__item-title">{{ item.title }}</h2>
            </div>
            <div class="summary-card__item-score">
              <p class="summary-card__item-score-value">
                {{ item.score }}
              </p>

               <p class="summary-card__item-score-max">
                / {{ item.maxScore }}
               </p>
            </div>
          </li>
        </ul>
        <el-button class="summary-card__button">Continue</el-button>
      </div>
    </article>
  </main>
</template>

<script setup lang="ts">
import type { SummaryItem } from '@/api/learn-demo/type';
import { ref } from 'vue';

const summaryList = ref<SummaryItem[]>([
  {
    iconPath: '/demo/result-summary/icon-reaction.svg',
    title: 'Reaction',
    score: 80,
    maxScore: 100,
    tone: 'reaction',
  },
  {
    iconPath: '/demo/result-summary/icon-memory.svg',
    title: 'Memory',
    score: 92,
    maxScore: 100,
    tone: 'memory',
  },
  {
    iconPath: '/demo/result-summary/icon-verbal.svg',
    title: 'Verbal',
    score: 61,
    maxScore: 100,
    tone: 'verbal',
  },
  {
    iconPath: '/demo/result-summary/icon-visual.svg',
    title: 'Visual',
    score: 72,
    maxScore: 100,
    tone: 'visual',
  },
]);
</script>

<style scoped lang="scss">
.result-summary-container {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(231, 238, 254);

  .result-summary-card {
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: #fff;
    border-radius: 20px;
    height: 480px;
    width: 700px;

    .result-card {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      gap: 20px;
      background-color: #5d3efb;
      border-radius: 30px;
      height: 100%;
      width: 50%;
      padding: 60px;
      color: #c5b8fc;

      .result-card__title {
        font-size: 24px;
        font-weight: 700;
      }

      .result-card__score {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 180px;
        aspect-ratio: 1;
        border-radius: 50%;
        background-color: #462ede;

        .result-card__score-value {
          font-size: 64px;
          font-weight: 700;
          margin-bottom: -16px;
          color: #fefcfb;
        }


        .result-card__score-max {
          font-size: 16px;
          font-weight: 400;
          color: #7263f0;
        }
      }

      .result-card__evaluate {
        font-size: 24px;
        font-weight: 700;
        color: #f6f8fe;
      }

      .result-card__description {
        font-size: 18px;
        font-weight: 400;
        text-align: center;
      }
    }

    .summary-card {
      display: flex;
      flex-direction: column;
      justify-content: center;
      background-color: #fff;
      border-radius: 20px;
      height: 100%;
      width: 50%;
      padding: 20px 40px;

      &__title {
        font-size: 24px;
        font-weight: 700;
        color: #3e4151;
      }

      &__list {

        .summary-card__item {
          display: flex;
          flex-direction: row;
          justify-content: space-between;
          align-items: center;
          border-radius: 12px;
          margin: 16px 0;
          height: 60px;
          padding: 0 20px;

          &--reaction {
            --summary-item-color: hsl(0deg 100% 67%);
            background-color: hsl(0deg 100% 67% / 8%);
          }

          &--memory {
            --summary-item-color: hsl(39deg 100% 56%);
            background-color: hsl(39deg 100% 56% / 8%);
          }

          &--verbal {
            --summary-item-color: hsl(166deg 100% 37%);
            background-color: hsl(166deg 100% 37% / 8%);
          }

          &--visual {
            --summary-item-color: hsl(234deg 85% 45%);
            background-color: hsl(234deg 85% 45% / 8%);
          }

          .left {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;

            .summary-card__item-title {
              font-size: 20px;
              color: var(--summary-item-color);
            }
          }


          .summary-card__item-score {

            &-value {
              display: inline;
              font-weight: 800;
              color: #303b59;
            }

            &-max {
              display: inline;
              color: #8b8c98;
            }
          }
        }
      }

      .summary-card__button {
          height: 54px;
          border-radius: 50px;
          font-size: 18px;
          font-weight: 500;
          color: #e3e5e9;
          background-color: #303b59;
        }
    }
  }

}
</style>
