<script setup>
import { ref } from 'vue';

const popover = ref();
const selectedQuizType = ref(null);
const quizType = ref(['Multiple Choice', 'Single Choice']);
</script>

<template>
    <Card>
        <template #title>Quiz erstellen</template>/
        <template #content>
            <p class="m-10">Erstellen hier ein neues Quiz</p>
        </template>
        <div class="card flex justify-center">
            <Button label="Quiztyp" icon="pi pi-chevron-circle-down" @click="popover.toggle"/>
        </div>
    </Card>
</template>

<style lang="scss" scoped></style>
