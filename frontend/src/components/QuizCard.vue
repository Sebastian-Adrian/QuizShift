<template>
  <div class="p-4 bg-white shadow rounded-lg">
    <h3 class="font-bold">{{ quiz.title }}</h3>
    <p class="text-gray-500">{{ quiz.description }}</p>
    <RouterLink :to="`/quiz/${quiz.id}`" class="text-blue-500">Quiz starten</RouterLink>
  </div>
</template>

<script setup>
defineProps(['quiz']);
</script>
