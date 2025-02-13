import { createRouter, createWebHistory } from 'vue-router'
import Home from "@/components/Home.vue";
import Login from "@/components/Login.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
    },
    {
      path: '/about',
      name: 'about',
      // Lazy-Loading
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
