import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
      redirect: '/home-page',
      children: [
        {
          path: 'home-page',
          name: 'home-page',
          component: () => import('../views/home-page/HomePageView.vue'),
        },
        {
          path: 'anime-download',
          name: 'anime-download',
          component: () => import('../views/anime-download/AnimeDownloadView.vue'),
        },
        {
          path: 'comic-read',
          name: 'comic-read',
          component: () => import('../views/comic-read/ComicReadView.vue'),
        },
        {
          path: 'code-generator',
          name: 'code-generator',
          component: () => import('../views/code-generator/CodeGeneratorView.vue'),
        },
        {
          path: 'learn-demo',
          name: 'learn-demo',
          // component: () => import('../views/learn-demo/QRView.vue'),
          redirect: '/learn-demo/qr-demo',
          children: [
            {
              path: 'qr-demo',
              name: 'qr-demo',
              component: () => import('../views/learn-demo/QRView.vue'),
            },
            {
              path: 'blog-demo',
              name: 'blog-demo',
              component: () => import('../views/learn-demo/BlogView.vue'),
            },
            {
              path: 'social-link-demo',
              name: 'social-link-demo',
              component: () => import('../views/learn-demo/SocialLink.vue'),
            },
          ]
        },
        {
          path: 'website-guide',
          name: 'website-guide',
          component: () => import('../views/web-guide/WebsiteGuideView.vue'),
        },
      ],
    },
  ],
})

export default router
