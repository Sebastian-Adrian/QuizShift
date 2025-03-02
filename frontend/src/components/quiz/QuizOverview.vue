<script setup>

import {onMounted, ref} from "vue";
import router from "@/router";
import api from "@/api/api";
import {useToast} from "primevue/usetoast";
import {useConfirm} from "primevue/useconfirm";

const confirm = useConfirm();
const toast = useToast();

const quizzes = ref([]);

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
            toast.add({ severity: 'info', summary: 'Confirmed', detail: 'Quiz gelöscht', life: 3000 });
        },
        reject: () => {
            toast.add({ severity: 'error', summary: 'Rejected', detail: 'Löschen Abgebrochen', life: 3000 });
        }
    });
};


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

        quizzes.value = await response.data.map(quiz => ({
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
        <Card v-for="quiz in quizzes" :key="quiz.id" :title="quiz.title" class="max-w-xl max-h-24 overflow-hidden">
            <template #content>
                <div class="flex items-center justify-between max-h-8">
                    <div class="flex flex-col">
                        <p class="text-lg">{{ quiz.title }}</p>
                        <p class="text-sm">{{ quiz.description }}</p>
                    </div>
                    <Button
                        class="ml-auto"
                        icon="pi pi-trash"
                        label="Delete"
                        outlined
                        severity="danger"
                        @click="confirmDelete($event)">
                    </Button>
                </div>
            </template>
        </Card>
    </div>
</template>

<style lang="scss" scoped>

</style>
