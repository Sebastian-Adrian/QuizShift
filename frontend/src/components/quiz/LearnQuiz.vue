<script setup>
import { ref } from 'vue';
import QuestionCard from "@/components/QuestionCard.vue";

const popover = ref();
const selectedQuizType = ref(null);
const quizType = ref(['Multiple Choice', 'Single Choice']);
</script>

<template>
    <div class="card">
        <h1>Lernen</h1>
        <QuestionCard>
        </QuestionCard>
    </div>
</template>

<style lang="scss" scoped></style>
