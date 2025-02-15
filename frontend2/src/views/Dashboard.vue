<script setup>
import StatsWidget from '@/components/dashboard/StatsWidget.vue';
import Rangliste from "@/views/Rangliste.vue";
import Fortschritt from "@/views/Fortschritt.vue";
</script>

<template>
    Globale Statistiken
    <div class="grid grid-cols-12 gap-8">
        <StatsWidget />
        <div class="col-span-4 xl:col-span-6">
            <Rangliste />
        </div>
        <div class="col-span-4 xl:col-span-6">
            <Fortschritt />
        </div>
    </div>
</template>
