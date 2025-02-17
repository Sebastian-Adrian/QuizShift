<script setup>
import { ref } from 'vue';

import AppMenuItem from './AppMenuItem.vue';

const model = ref([
    {
        label: 'Home',
        items: [{ label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/' }]
    },
    {
        label: 'Erfolge ?',
        items: [
            { label: 'Achievements', icon: 'pi pi-fw pi-trophy', to: '/achievements' },
            { label: 'Fortschritt', icon: 'pi pi-fw pi-star', to: '/fortschritt' },
            { label: 'Rangliste', icon: 'pi pi-fw pi-crown', to: '/rangliste', class: 'rotated-icon' },
            { label: 'Coins / Punkte', icon: 'pi pi-fw pi-money-bill', to: '/coins' },
            { label: 'Powerups', icon: 'pi pi-fw pi-clock', to: '/powerups' },
        ]
    },
    {
        label: 'Skill',
        icon: 'pi pi-fw pi-briefcase',
        to: '/pages',
        items: [
            {
                label: 'Quiz',
                icon: 'pi pi-fw pi-globe',
                to: '/quiz'
            },
            {
                label: 'Lernen',
                icon: 'pi pi-fw pi-graduation-cap',
                to: '/lernen'
            },
            {
                label: 'Not Found',
                icon: 'pi pi-fw pi-exclamation-circle',
                to: '/notfound'
            },
            {
                label: 'Empty',
                icon: 'pi pi-fw pi-circle-off',
                to: '/empty'
            },
        ]
    },
    {
        label: 'Bonus',
        items: [
            {
                label: 'Bonus 1',
                icon: 'pi pi-fw pi-bookmark',
                to: '/'
            },
            {
                label: 'Bonus 2',
                icon: 'pi pi-fw pi-bookmark',
                to: '/',
            }
        ]
    },
    {
        label: 'Get Started',
        items: [
            {
                label: 'Dokumentation',
                icon: 'pi pi-fw pi-book',
                to: '/documentation'
            },
            {
                label: 'View Source',
                icon: 'pi pi-fw pi-github',
                url: 'https://github.com/Sebastian-Adrian/QuizShift',
                target: '_blank'
            }
        ]
    }
]);
</script>

<template>
    <ul class="layout-menu">
        <template v-for="(item, i) in model" :key="item">
            <app-menu-item v-if="!item.separator" :index="i" :item="item"></app-menu-item>
            <li v-if="item.separator" class="menu-separator"></li>
        </template>
    </ul>
</template>

<style lang="scss" scoped></style>
