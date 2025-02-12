<template>
  <div>
    <h1 class="text-2xl font-bold">Verfügbare Quizzes</h1>
    <div class="grid grid-cols-3 gap-4">
      <QuizCard v-for="quiz in quizStore.quizzes" :key="quiz.id" :quiz="quiz" />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useQuizStore } from '../stores/quizStore';
import QuizCard from '../components/QuizCard.vue';

const quizStore = useQuizStore();

onMounted(() => {
  quizStore.fetchQuizzes();
});
</script>
