<script setup>

import QuizOverview from "@/components/quiz/QuizOverview.vue";
import CreateQuiz from "@/components/quiz/CreateQuiz.vue";
</script>

<template>
    <div>
        <QuizOverview>
        </QuizOverview>
        <CreateQuiz>
        </CreateQuiz>
    </div>
</template>

<style lang="scss" scoped>

div {
    display: flex;
    flex-direction: column;
    gap: 5rem;
}
</style>
