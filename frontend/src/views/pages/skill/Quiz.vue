<script setup>
import { ref } from 'vue';
import QuestionCard from "@/views/pages/skill/QuestionCard.vue";

const popover = ref();
const selectedQuizType = ref(null);
const quizType = ref(['Multiple Choice', 'Single Choice']);
</script>

<template>
    <div class="card">
        <QuestionCard>
        </QuestionCard>
    </div>
</template>

<style lang="scss" scoped></style>
