<script setup>
import { ref } from 'vue';
import QuizCard from "@/views/pages/skill/QuizCard.vue";

const popover = ref();
const selectedQuizType = ref(null);
const quizType = ref(['Multiple Choice', 'Single Choice']);
</script>

<template>
    <div class="card">
        <QuizCard>
        </QuizCard>
    </div>
</template>

<style lang="scss" scoped></style>
