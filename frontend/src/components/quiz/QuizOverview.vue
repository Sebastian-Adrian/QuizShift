<script setup>

import {onMounted, ref} from "vue";
import router from "@/router";
import api from "@/api/api";
import {useToast} from "primevue/usetoast";
import {useConfirm} from "primevue/useconfirm";
import {useQuizStore} from "@/stores/quizStore";
import InputText from "primevue/inputtext";
import FloatLabel from "primevue/floatlabel";

const confirm = useConfirm();
const toast = useToast();

const quizStore = useQuizStore();
const {quizzes} = quizStore

const confirmDelete = (event) => {
    confirm.require({
        target: event.currentTarget,
        message: 'Bist du sicher?',
        icon: 'pi pi-info-circle',
        rejectProps: {
            label: 'Abbrechen',
            severity: 'secondary',
            outlined: true
        },
        acceptProps: {
            label: 'Löschen',
            severity: 'danger'
        },
        accept: () => {
            toast.add({ severity: 'info', summary: 'Gelöscht', detail: 'Quiz wurde gelöscht', life: 3000 });
        },
        reject: () => {
            toast.add({ severity: 'error', summary: 'Abgebrochen', detail: 'Löschen wurde Abgebrochen', life: 3000 });
        }
    });
};

const updateQuiz = async (quiz) => {

    /*
    * TODO: Authorization
    */

    try {
        const response = await api.put(`/quiz/${quiz.id}`, quiz);

        if(response.status === 200) {
            toast.add({
                severity: "success",
                summary: "Gespeichert",
                detail: "Das Quiz wurde gespeichert!",
                life: 3000
            });

            quizzes.value = quizzes.value.map(q => q.id === quiz.id ? response.data : q);

        } else {
            toast.add({
                severity: "error",
            })
            return
        }

        quiz.editMode = !quiz.editMode;


    } catch (e){
        console.error('Error: ', e.message || e.toString())
    }
};

const editQuiz = (quiz) => {
    quiz.editMode = !quiz.editMode;
}


onMounted( async () => {
    try {
        const token = localStorage.getItem('token');
        if (!token) {
            await router.push('/login');
        }

        const response = await api.get('/quiz', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        quizStore.quizzes = response.data.map(quiz => ({
            editMode: false,
            id: quiz.id,
            title: quiz.title,
            description: quiz.description,
        }));

    } catch (e) {
        console.error('Error: ', e.message || e.toString())
    }
})

</script>

<template>
    <Toast/>
    <ConfirmPopup></ConfirmPopup>
    <div class="flex flex-col gap-3 ">
        <Card v-for="quiz in quizStore.quizzes" :key="quiz.id" :title="quiz.title" class="max-w-xl max-h-36 overflow-hidden">
            <template #content>
                <div class="flex items-center justify-between max-h-20">
                    <div class="flex flex-col gap-2">
                        <div>
                            <div v-if="quiz.editMode">
                                <FloatLabel variant="on">
                                    <InputText id="title" v-model="quiz.title" class="max-w-80" size="small"/>
                                    <label for="title">Titel</label>
                                </FloatLabel>
                            </div>
                            <p v-else class="text-lg">{{ quiz.title }}</p>
                        </div>
                        <div>
                            <div v-if="quiz.editMode">
                                <FloatLabel variant="on">
                                    <InputText v-if="quiz.editMode" id="description" v-model="quiz.description" class="max-w-80"
                                               size="small"/>
                                    <label for="description">Beschreibung</label>
                                </FloatLabel>
                            </div>
                            <p v-else class="text-sm">{{ quiz.description }}</p>
                        </div>
                    </div>
                    <div class="ml-auto flex gap-1">
                        <Button v-if="quiz.editMode"
                        :disabled="!quiz.editMode"
                        icon="pi pi-check"
                        label="Save"
                        outlined
                        @click="updateQuiz(quiz)">
                        </Button>
                        <Button
                            v-else
                            icon="pi pi-pencil"
                            label="Edit"
                            outlined
                            @click="editQuiz(quiz)"
                        >
                        </Button>
                        <Button
                            icon="pi pi-trash"
                            label="Delete"
                            outlined
                            severity="danger"
                            @click="confirmDelete($event)">
                        </Button>
                    </div>
                </div>
            </template>
        </Card>
    </div>
</template>

<style lang="scss" scoped>

</style>
