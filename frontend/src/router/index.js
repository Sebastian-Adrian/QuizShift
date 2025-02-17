import AppLayout from '@/layout/AppLayout.vue';
import LoginPage from '@/views/pages/auth/Login.vue'; // Login-Seite

import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/stores/auth.ts';

const index = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: AppLayout,
            meta: { requiresAuth: false },
            children: [
                {
                    path: '/',
                    name: 'dashboard',
                    component: () => import('@/views/Dashboard.vue'),
                    meta: {requiresAuth: true}
                },
                {
                    path: '/empty',
                    name: 'empty',
                    component: () => import('@/views/pages/Empty.vue')
                },
                {
                    path: '/documentation',
                    name: 'documentation',
                    component: () => import('@/views/pages/Documentation.vue'),
                    meta: {requiresAuth: true}
                },
                {
                    path: '/achievements',
                    name: 'achievements',
                    component: () => import('@/views/pages/erfolge/Achievements.vue'),
                    meta: {requiresAuth: true}
                }


            ]
        },
        {
            path: '/landing',
            name: 'landing',
            component: () => import('@/views/pages/Landing.vue')
        },
        {
            path: '/notfound',
            name: 'notfound',
            component: () => import('@/views/pages/NotFound.vue')
        },

        {
            path: '/auth/login',
            name: 'login',
            component: () => import('@/views/pages/auth/Login.vue')
        },
        {
            path: '/login',
            name: 'Login',
            component: LoginPage
        },

        {
            path: '/auth/access',
            name: 'accessDenied',
            component: () => import('@/views/pages/auth/Access.vue')
        },
        {
            path: '/auth/error',
            name: 'error',
            component: () => import('@/views/pages/auth/Error.vue')
        }
    ]
});

// Route Guard: Prüft, ob die Route Authentifizierung erfordert
index.beforeEach((to, from, next) => {
    const authStore = useAuthStore();
    // Prüfen, ob die Route geschützt ist
    if (to.meta.requiresAuth && !authStore.isAuthenticated) {
        next({ name: 'login' });
        // Benutzer ist eingeloggt und versucht die Login-Seite zu erreichen → Weiterleitung zur Startseite
    } else if (to.name === 'login' && authStore.isAuthenticated) {
        next({name: 'dashboard'});
    } else {
        next();
    }
});
export default index;
