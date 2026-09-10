<template>
  <div class="container">
    <div class="text-container">
      <div class="text__slogan">Reliable, efficient delivery</div>
      <div class="text__title">Powered by Technology</div>
      <div class="text__desc">
        Our Artificial Intelligence powered tools use millions of project data points to ensure that your project is successful
      </div>
    </div>
    <div class="card-container">
      <div class="card-item">卡片1</div>
      <div class="card-item">卡片2</div>
      <div class="card-item">卡片3</div>
      <div class="card-item">卡片4</div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
.container {
  width: 1050px;
  height: 750px;
  margin: auto;
  background-color: #fafafa;
  padding: 60px;

  .text-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 60px;


    .text__slogan {
      color: #7c7c8a;
      font-size: 26px;
    }

    .text__title {
      color: #4d4d5f;
      font-size: 30px;
      font-weight: 700;
    }

    .text__desc {
      color: #7c7c8a;
      font-size: 12px;
      width: 450px;
      text-align: center;
    }
  }

  .card-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-template-rows: repeat(2, 1fr);
    gap: 20px;

    .card-item {
      width: 250px;
      height: 180px;
      background-color: #fff;
    }

    .card-item:nth-child(1) {
      grid-row: 1;
      grid-row: 1 / 3;
      align-self: center;
      background-color: yellow;
    }

    .card-item:nth-child(2) {
      grid-column: 2;
      grid-row: 1;
    }

    .card-item:nth-child(3) {
      grid-column: 2;
      grid-row: 2;
    }

    .card-item:nth-child(4) {
      grid-row: 1;
      grid-row: 1 / 3;
      align-self: center;
      background-color: blue;
    }
  }
}
</style>
