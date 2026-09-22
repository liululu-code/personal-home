<template>
  <div class="app-layout">
    <aside class="sidebar" :class="{ 'sidebar--open': isMobileMenuOpen }">
      <div class="sidebar-header">
        <div class="sidebar-header-left">
          <img
            src="/demo/hotel-booking-confirmation-page/icon-sun.svg"
            alt="logo"
            class="sidebar-header-logo"
          />
          <div class="sidebar-header-title">
            <p class="sidebar-header-title-up">Maison</p>
            <p class="sidebar-header-title-down">Soleil</p>
          </div>
        </div>
        <div class="sidebar-header-right">
          <img
            src="/demo/hotel-booking-confirmation-page/icon-menu.svg"
            alt=""
            @click="toggleMobileMenu"
          />
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
          <div class="sidebar-content-weather__temperature">27°</div>
          <div class="sidebar-content-weather__desc">Sunny · light breeze</div>
        </div>
      </div>

      <div class="sidebar-footer">
        <p>EST. 1007</p>
        <p>MAISON SOLETL * 12 RUE DES OLIVIEAS * CASSIS</p>
        <p>@ 2026 MAISON SOLETL. 1007</p>
      </div>
    </aside>
    <main class="main-content" :class="{ 'main-content--open': isMobileMenuOpen }">
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
      <div class="main-content-hero">
        <div class="main-content-hero__card">
          <div class="main-content-hero__card__receipt-card main-content-hero__card__common-card">
            This is Receipt Card.This is Receipt Card.
          </div>
          <div class="main-content-hero__card__note-card main-content-hero__card__common-card">
            This is Note Card.
          </div>
        </div>
        <div class="main-content-hero__text">HOVER TO FAN</div>
      </div>
      <div class="main-content-cards">
        <!-- 第1张 -->
        <HotelServiceCard :item="cardItems[0]" :style="{ '--card-color': cardItems[0].color }">
          <template #content>
            <p>
              {{ cardItems[0].desc }}
            </p>
          </template>
        </HotelServiceCard>

        <!-- 第2张 -->
        <HotelServiceCard :item="cardItems[1]" :style="{ '--card-color': cardItems[1].color }">
          <template #content>
            <div class="wifi-info">
              <div class="wifi-row">
                <span class="wifi-label"> NETWORK </span>
                <span>
                  {{ cardItems[1].network }}
                </span>
              </div>

              <div class="wifi-row">
                <span class="wifi-label"> PASSWORD </span>
                <div class="wifi-row__password">
                  <span>
                    {{ cardItems[1].password }}
                  </span>
                  <button>COPY</button>
                </div>
              </div>
            </div>
          </template>
        </HotelServiceCard>

        <!-- 第3张 -->
        <HotelServiceCard :item="cardItems[2]" :style="{ '--card-color': cardItems[2].color }">
          <template #content>
            <p>
              {{ cardItems[2].desc }}
            </p>
          </template>
        </HotelServiceCard>
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import HotelServiceCard from './HotelServiceCard.vue'

const navItems = [
  {
    id: 1,
    icon: '/demo/hotel-booking-confirmation-page/icon-bed.svg',
    name: 'Your stay',
  },
  {
    id: 2,
    icon: '/demo/hotel-booking-confirmation-page/icon-house.svg',
    name: 'The house',
  },
  {
    id: 3,
    icon: '/demo/hotel-booking-confirmation-page/icon-pin.svg',
    name: 'Around Town',
  },
  {
    id: 4,
    icon: '/demo/hotel-booking-confirmation-page/icon-breakfast-outline.svg',
    name: 'Breakfast',
  },
  {
    id: 5,
    icon: '/demo/hotel-booking-confirmation-page/icon-mail.svg',
    name: 'Messages',
  },
]

const cardItems = [
  {
    id: 1,
    icon: '/demo/hotel-booking-confirmation-page/icon-key.svg',
    name: 'ARRIVAL',
    color: '#c84f20',
    sequenceNum: '01',
    title: 'Check-in from 15:00',
    subTitle: 'Sat, 25 April',
    desc: `Ring the brass bell by the blue door.
           If we're at the market, the key is in the
           terracotta pot by the olive tree.`,
  },

  {
    id: 2,
    icon: '/demo/hotel-booking-confirmation-page/icon-wifi.svg',
    name: 'WIFI',
    color: '#5269c7',
    sequenceNum: '02',
    title: 'Le Soleil · Guest',
    subTitle: 'Password below',
    network: 'Le Soleil · Guest',
    password: 'soleil-2026',
  },

  {
    id: 3,
    icon: '/demo/hotel-booking-confirmation-page/icon-breakfast.svg',
    name: 'BREAKFAST',
    color: '#c54272',
    sequenceNum: '03',
    title: 'Served 8 – 10:30',
    subTitle: 'On the terrace',
    desc: `Fresh figs, Marseille honey, pain au levain,
           and espresso. Gluten-free option?
           Leave a note the night before.`,
  },
]

const isMobileMenuOpen = ref(false)
const isMainContentOpen = ref(true)

const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value
  isMainContentOpen.value = !isMainContentOpen.value
}
</script>

<style scoped lang="scss">
@use '@/assets/styles/breakpoints' as breakpoint;

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
    width: clamp(120px, 20%, 300px);
    flex-shrink: 1;
    padding: 10px;
    border-right: 1px solid #130602;

    &-header {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      height: 80px;
      padding: 10px;
      border-bottom: 1px solid #130602;

      &-left {
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

      &-right {
        display: none;
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
            padding: 15px;
            gap: 10px;
          }

          &-item:hover {
            border-radius: 5px;
            background-color: #ffffff;
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

        &__temperature {
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
    container-name: main;
    container-type: inline-size;
    flex: 1;
    min-width: 0;
    display: flex;
    flex-direction: column;
    padding: 30px;

    &-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      flex-wrap: wrap;
      height: auto;
      min-height: 80px;

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
            color: #e42424;
          }
        }
      }

      &__right {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 20px;
        height: 40px;

        &__print-receipt {
          height: 100%;
          padding: 0 20px;
          border-radius: 20px;
          border: 1px solid #ded8cf;
          background-color: #fbf6f0;
        }

        &__add-to-calendar {
          height: 100%;
          padding: 0 20px;
          border: none;
          border-radius: 20px;
          background-color: #2b2620;
          color: #fbf6f0;
        }
      }
    }
    &-hero {
      flex: 1;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      width: 100%;
      margin: 40px 0;

      &__card {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        width: 100%;

        &__common-card {
          display: flex;
          flex-shrink: 0;
          justify-content: center;
          align-items: center;
          width: clamp(200px, 42%, 350px);
          aspect-ratio: 7 / 8;
          border-radius: 20px;
        }

        &__receipt-card {
          transform: rotate(-3deg);
          background-color: #f5f4f1;
          box-shadow:
            0 20px 40px #919079,
            0 8px 20px rgba(80, 45, 20, 0.08);
        }

        &__note-card {
          transform: rotate(3deg);
          background-color: #9f3e1d;
          box-shadow:
            0 20px 40px #919079,
            0 8px 20px #9f3e1d;
        }
      }

      &__text {
        margin-top: 20px;
        font-size: 12px;
        color: #9f3e1d;
      }
    }
    &-cards {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(min(260px, 100%), 1fr));
      gap: 20px;

      .wifi-info {
        display: flex;
        flex-direction: column;
        gap: 4px;
      }

      .wifi-row {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        height: 30px;
        border-radius: 6px;
        padding: 10px;
        background-color: #f2efea;

        &__password {
          display: flex;
          gap: 8px;
          align-items: center;

          > button {
            height: 16px;
            border-radius: 5px;
            border: none;
            padding: 0 4px;
            line-height: 16px;
            background-color: #e4dbd0;
            font-size: 8px;
          }
        }
      }
    }
  }
}

@media (max-width: breakpoint.$mobile) {
  .app-layout {
    flex-direction: column;

    .sidebar {
      width: 100%;
      border-right: none;

      .sidebar-content {
        display: none;
      }

      .sidebar-footer {
        display: none;
      }

      &-header {
        &-right {
          display: flex;
        }
      }

      &--open {
        .sidebar-content {
          display: flex;
          height: 100vh;
        }
      }
    }

    .main-content {
      display: flex;

      &-header {
        justify-content: center;
      }

      &__card {
        flex-direction: column;
      }

      &-hero__card__common-card {
        width: clamp(200px, 84%, 400px);
      }

      &-cards {
        grid-template-columns: repeat(1, minmax(min(260px, 100%), 1fr));
      }
    }

    .main-content--open {
      display: none;
    }
  }
}

// @media (max-width: 768px) {

//   .sidebar-content-nav-list {
//     display: flex;
//     gap: 8px;
//     overflow-x: auto;
//   }

//   .sidebar-content-nav-list-item {
//     flex-shrink: 0;
//     padding: 0 12px;
//   }
// }
</style>
