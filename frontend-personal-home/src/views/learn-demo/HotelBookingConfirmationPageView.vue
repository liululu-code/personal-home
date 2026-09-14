<template>
  <div class="app-layout">
    <aside class="sidebar">
      <div class="sidebar-header">
        <img
          src="/public/demo/hotel-booking-confirmation-page/icon-sun.svg"
          alt="logo"
          class="sidebar-header-logo"
        />
        <div class="sidebar-header-title">
          <p class="sidebar-header-title-up">Maison</p>
          <p class="sidebar-header-title-down">Soleil</p>
        </div>
      </div>

      <div class="sidebar-content">
        <div class="sidebar-content-nav">
          <ul class="sidebar-content-nav-list">
            <li class="sidebar-content-nav-list-item" v-for="item in navItems" :key="item.id">
              <img :src="item.icon" alt="icon" class="sidebar-content-nav-list-item-icon" />
              <p class="sidebar-content-nav-list-item-title">
                {{ item.name }}
              </p>
            </li>
          </ul>
        </div>
        <div class="sidebar-content-weather">
          <img
            class="sidebar-content-weather__icon"
            src="/demo/hotel-booking-confirmation-page/icon-weather.svg"
            alt=""
          />
          <div class="sidebar-content-weather__title">TODAY IN CASSIS</div>
          <div class="sidebar-content-weather__temprature">27°</div>
          <div class="sidebar-content-weather__desc">Sunny · light breeze</div>
        </div>
      </div>

      <div class="sidebar-footer">
        <p>EST. 1007</p>
        <p>MAISON SOLETL * 12 RUE DES OLIVIEAS * CASSIS</p>
        <p>@ 2026 MAISON SOLETL. 1007</p>
      </div>
    </aside>
    <main class="main-content">
      <div class="main-content-header">
        <div class="main-content-header__left">
          <div class="main-content-header__left__upper">BOOKING * CONFIRMED</div>
          <div class="main-content-header__left__bottom">
            <span class="main-content-header__left__bottom__welcome">Bienvenue, </span>
            <span class="main-content-header__left__bottom__name">Lucia</span>
          </div>
        </div>
        <div class="main-content-header__right">
          <button class="main-content-header__right__print-receipt">Print receipt</button>
          <button class="main-content-header__right__add-to-calendar">Add to calendar</button>
        </div>
      </div>
      <div class="main-content-hero"></div>
      <div class="main-content-cards"></div>
    </main>
  </div>
</template>

<script setup lang="ts">
const navItems = [
  {
    id: 1,
    icon: '/public/demo/hotel-booking-confirmation-page/icon-bed.svg',
    name: 'Your stay',
  },
  {
    id: 2,
    icon: '/public/demo/hotel-booking-confirmation-page/icon-house.svg',
    name: 'The house',
  },
  {
    id: 3,
    icon: '/public/demo/hotel-booking-confirmation-page/icon-pin.svg',
    name: 'Around Town',
  },
  {
    id: 4,
    icon: '/public/demo/hotel-booking-confirmation-page/icon-breakfast-outline.svg',
    name: 'Breakfast',
  },
  {
    id: 5,
    icon: '/public/demo/hotel-booking-confirmation-page/icon-mail.svg',
    name: 'Messages',
  },
]
</script>

<style scoped lang="scss">
.app-layout {
  display: flex;
  flex-direction: row;
  flex: 1;
  max-width: 1440px;
  margin: 0 auto;
  background-color: #faeee3;

  .sidebar {
    display: flex;
    flex-direction: column;
    width: 250px;
    flex-shrink: 0;
    padding: 10px;
    border-right: 1px solid #130602;

    &-header {
      display: flex;
      gap: 10px;
      flex-direction: row;
      align-items: center;
      height: 80px;
      padding: 10px;
      border-bottom: 1px solid #130602;

      &-logo {
        width: 40px;
        aspect-ratio: 1/1;
      }
      &-title {
        display: flex;
        flex-direction: column;
        line-height: 1;

        &-up {
          font-size: 18px;
          font-weight: 600;
          color: #966a59;
        }
        &-down {
          font-size: 18px;
          font-weight: 600;
          color: #130602;
        }
      }
    }

    &-content {
      flex: 1;
      display: flex;
      flex-direction: column;
      padding: 10px 0;
      border-bottom: 1px solid #130602;
      // background-color: #252077;

      &-nav {
        flex: 1;
        // background-color: #a80d0d;

        &-list {
          &-item {
            display: flex;
            flex-direction: row;
            align-items: center;
            height: 40px;
            gap: 10px;
          }
        }
      }

      &-weather {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: 12px 20px;
        border-radius: 20px;
        height: 110px;
        background-color: #f4d98a;
        position: relative;
        overflow: hidden;

        &__icon {
          position: absolute;
          top: -24px;
          right: -16px;
          width: 76px;
          height: 76px;
        }

        &__title {
          font-size: 14px;
        }

        &__temprature {
          font-size: 26px;
          font-weight: 700;
        }

        &__desc {
          font-size: 14px;
        }
      }
    }
    &-footer {
      display: flex;
      flex-direction: column;
      gap: 10px;
      height: 110px;
      font-size: 12px;
      padding: 10px 20px 10px 0;
    }
  }

  .main-content {
    flex: 1;
    display: flex;
    flex-direction: column;
    padding: 30px;

    &-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 80px;

      &__left {
        display: flex;
        flex-direction: column;

        justify-content: space-between;

        &__upper {
          font-size: 14px;
        }

        &__bottom {
          font-size: 36px;

          &__name {
            color: #e42424
          }
        }
      }

      &__right {
        display: flex;
        gap: 20px;
        height: 40px;
      }

    }
    &-hero {
      flex: 1;
      background-color: #1c0da8;
    }
    &-cards {
      height: 300px;
      background-color: #1f9c1b;
    }
  }
}
</style>
