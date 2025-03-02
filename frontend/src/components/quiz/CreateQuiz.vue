<script setup>
import QuestionForm from "@/components/QuestionForm.vue";
import InputText from "primevue/inputtext";
import FloatLabel from "primevue/floatlabel";
import {ref} from "vue";
import {useToast} from "primevue/usetoast";
import {useQuizStore} from "@/stores/quizStore";
import api from "@/api/api";

const newQuiz = ref({'title': '', 'description': '', 'questions': []});
const toast = useToast();
const quizStore = useQuizStore();

const createQuiz = async () => {

    if (!newQuiz.value.title || !newQuiz.value.description) {
        toast.add({
            severity: 'warn',
            summary: 'Unvollständig',
            detail: 'Bitte gebe einen Titel und eine Beschreibung ein!',
            life: 3000 });

        return;
    }

    try {

        const response = await api.post('/quiz', newQuiz.value);

        if (response.status === 200) {
            quizStore.addQuiz({...newQuiz.value});

            toast.add({
                severity: "success",
                summary: "Gespeichert",
                detail: "Das Quiz wurde erstellt!",
                life: 3000,
            });
            // Formular leeren
            newQuiz.value = {title: "", description: "", questions: []};
        } else {
            toast.add({
                severity: "error",
                summary: "Fehler",
                detail: "Das Quiz konnte nicht erstellt werden!",
                life: 3000,
            });
        }
    } catch (e) {

    }


};


</script>

<template>
    <Card class="max-w-xl max-h-32 overflow-hidden">
        <Toast/>
        <template #content>
            <div class="flex items-center justify-between">
                <div class="flex flex-col gap-1">
                    <FloatLabel variant="on">
                        <InputText v-model="newQuiz.title" class="max-w-80"/>
                        <label for="title">Titel</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="newQuiz.description" class="max-w-80"/>
                        <label for="description">Beschreibung</label>
                    </FloatLabel>
                </div>
                <Button class="ml-auto" icon="pi pi-check" label="Erstellen" outlined @click="createQuiz"/>
            </div>
        </template>
    </Card>
</template>
<style lang="scss" scoped></style>
