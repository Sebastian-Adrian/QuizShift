import AppLayout from '@/layout/AppLayout.vue';
import LoginPage from '@/views/pages/auth/Login.vue'; // Login-Seite

import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/stores/auth.ts';
import api from "@/api/api";

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
                    meta: { requiresAuth: true }
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
                    meta: { requiresAuth: true }
                },
                {
                    path: '/achievements',
                    name: 'achievements',
                    component: () => import('@/views/pages/erfolge/Achievements.vue'),
                    meta: { requiresAuth: true }
                },
                {
                    path: '/learn-quiz/:id',
                    name: 'quiz',
                    component: () => import('@/views/pages/quiz/LearnQuiz.vue'),
                    meta: { requiresAuth: true }
                },
                {
                    path: '/create-quiz',
                    name: 'create-quiz',
                    component: () => import('@/views/pages/quiz/CreateQuiz.vue'),
                    meta: { requiresAuth: true }
                },
                {
                    path: '/quiz',
                    name: 'quiz',
                    component: () => import('@/views/pages/quiz/Quiz.vue'),
                    meta: { requiresAuth: true }
                }
            ]
        },
        {
            path: '/landing',
            name: 'landing',
            component: () => import('@/views/pages/Landing.vue'),
        },
        {
            path: '/notfound',
            name: 'notfound',
            component: () => import('@/views/pages/NotFound.vue'),
        },

        {
            path: '/auth/login',
            name: 'login',
            component: () => import('@/views/pages/auth/Login.vue'),
            meta: { requiresAuth: false }
        },
        {
            path: '/login',
            name: 'Login',
            component: LoginPage,
            meta: { requiresAuth: false }
        },

        {
            path: '/auth/access',
            name: 'accessDenied',
            component: () => import('@/views/pages/auth/Access.vue'),
        },
        {
            path: '/auth/error',
            name: 'error',
            component: () => import('@/views/pages/auth/Error.vue')
        }
    ]
});

// Route Guard: Prüft, ob die Route Authentifizierung erfordert
index.beforeEach(async (to, from, next) => {
    const authStore = useAuthStore();
    const token = authStore.token;

    if (to.meta["requiresAuth"]) {
        if (!token) {
            next({name: 'login'});
        } else {
            try {
                // Token prüfen
                const response = await api.post('/token/verify', {token});
                if (response.data) {
                    next();
                } else {
                    authStore.logout();
                    next({name: 'login'});
                }
            } catch (error) {
                console.error('Token-Validierung fehlgeschlagen:', error)
                authStore.logout();
                next({name: 'login'});
            }
        }
    } else if (to.name === 'login' && authStore.isAuthenticated) {
        next({name: 'dashboard'});
    } else {
        next();
    }
});
export default index;
