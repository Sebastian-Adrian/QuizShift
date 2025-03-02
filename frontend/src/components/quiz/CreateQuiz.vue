<script setup>
import QuestionForm from "@/components/QuestionForm.vue";
import InputText from "primevue/inputtext";
import FloatLabel from "primevue/floatlabel";
import {ref} from "vue";

const newQuiz = ref({'title': '', 'description': '', 'questions': []});

const createQuiz = () => {


};


</script>

<template>
    <Card class="max-w-xl max-h-32 overflow-hidden">
        <template #content>
            <div class="flex items-center justify-between">
                <div class="flex flex-col gap-1">
                    <FloatLabel variant="on">
                        <InputText class="max-w-80"/>
                        <label for="title">Titel</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText class="max-w-80"/>
                        <label for="description">Beschreibung</label>
                    </FloatLabel>
                </div>
                <Button class="ml-auto" icon="pi pi-check" label="Erstellen" outlined/>
            </div>
        </template>
    </Card>
</template>
<style lang="scss" scoped></style>
