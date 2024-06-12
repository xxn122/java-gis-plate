import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/views/LoginPage.vue')
    },
    {
      path: '/layout',
      name: 'layout',
      component: () => import('@/views/LayoutContainer.vue'),
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('@/views/AdministratorSide.vue'),
    }
  ]
})

export default router
