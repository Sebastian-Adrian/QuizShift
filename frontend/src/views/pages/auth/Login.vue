<script setup>
import FloatingConfigurator from '@/components/FloatingConfigurator.vue';

import { ref } from 'vue';
import api from "@/api/api";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";
import axios from "axios"; // Pinia Store für Authentifizierung

const username = ref('');
const password = ref('');
const checked = ref(false); // Für den "Merken"-Checkbox
const errorStatus = ref(null);
const router = useRouter();
const authStore = useAuthStore();


const loginUser = async () => {
    try {
        errorStatus.value = null;
        const response = await api.post('/auth/login', {
            username: username.value,
            password: password.value,
        });

        // Erfolgreiche Anmeldung
        const token = response.data;
        authStore.setToken(token); // Token im Store speichern
        authStore.setUser({
            username: username.value,
        });
        // Token als Standard-Header für alle zukünftigen Anfragen setzen
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        await router.push("/");
    } catch (error) {
        console.log(error);
        if (error.response && error.response.status === 401) {
            errorStatus.value = "Ungültige Anmeldedaten.";
        } else {
            errorStatus.value = "Ein Fehler ist aufgetreten. Bitte später erneut versuchen.";
        }
    }
};
</script>

<template>
    <FloatingConfigurator />
    <div class="bg-surface-50 dark:bg-surface-950 flex items-center justify-center min-h-screen min-w-[100vw] overflow-hidden">
        <div class="flex justify-center">
            <svg viewBox="0 0 459.16 576.32" xmlns="http://www.w3.org/2000/svg">
                <g id="Ebene_2" data-name="Ebene 2">
                    <g id="Ebene_1-2" data-name="Ebene 1">
                        <path class="cls-1"
                              d="M180.85,458.94c.8.35,13.23.27,14,.27,16,.06,72.16-.19,84.05.11,8.6-.18,15.33,3.71,21.05,10.07,5.95,6.63,5.81,13.84-1.5,19.27-5.45,4-6.65,3.44-3.36,12.86,5.13,14.69-.23,25-14.82,29.24a6.41,6.41,0,0,0-3.65,4.35c-2.22,16.21-11,28.48-25.34,34.74-10.06,4.39-21.81,5-33.29,2.41-19.76-4.52-32.55-17.69-35.6-37.6a11.46,11.46,0,0,1-2.88-3.89c-.2-.55-8.17-5.59-9.85-7.61-4-4.85-4.93-10.43-3.62-16.46.52-2.44,1.08-4.87,1.7-7.29,1.3-5,2.27-9.2-3.74-12.9s-5.76-12.26,0-16.72c4.8-3.71,10.34-6.47,15.5-9.72a15.48,15.48,0,0,0,2.26-2.18,25.68,25.68,0,0,0-2.55-2c-8.83-5.37-19.31-4.45-28.74-7.41-12.3-3.87-25.34-5.87-36.5-12.75-6.88-4.24-13.5-8.13-20.53-12a66.94,66.94,0,0,1-17.28-14c-5.35-6.05-10.8-12-16.23-18-2.91-3.21-6.13-6.16-8.73-9.59-11.84-15.54-21.91-32.07-27.35-51-1.54-5.38-2.73-10.86-4.46-16.16a140.18,140.18,0,0,0-5.59-14.05c-1-2.15-8.38-32.64-9.65-44.26-3.63-33.17-1.79-65.89,8.85-97.73C26.1,119.8,47.72,86.3,78.79,59.08,100,40.53,124.31,27.2,150.44,17.22A228.6,228.6,0,0,1,227.12,2.56c35.29-.84,68.69,7.45,100.58,22.2,57.91,26.79,96,71.21,116.8,131.16,7.49,21.6,11.92,43.81,12.15,66.52.4,39.51-6.77,77.43-26.79,112.26-12.19,21.21-24.63,39.51-39.47,57.12-.61.72-7.45,8.78-9.55,11.34-9.46,11.53-22,19.32-34.29,27-12.84,8-25.6,16.69-41.4,18.8-5.38.71-16.32,8.87-21.62,10.55m-27.38-39.08c0,10.63.33,8.59,9.57,8.86,7.37.22,7.38.11,7.28-7.42-.32-24.32-.43-48.64-1.13-72.94-.17-6.05,1.61-9.36,7.05-12,20-9.75,37.74-22.73,51.47-40.37C350.21,271.1,358.78,242,357,209.75c-2.19-40.44-21.23-71.48-53.21-95.11a117.94,117.94,0,0,0-61.55-22.81c-19.26-1.56-38.45,0-56.66,6.61A139.58,139.58,0,0,0,139,126.8c-13.24,12.11-24.16,26.22-30.62,42.5-5,12.74-7.24,27.15-8,41-1.84,35.2,9.37,66.47,32,93.63,8.48,10.18,19,17.55,30.54,23.77,3.73,2,5.51.83,6.22-3.13,1-5.94-2.15-9.64-6.24-13.23-7.64-6.68-15.88-12.91-22.37-20.6-15.45-18.3-23.44-39.86-25.89-63.77a98.43,98.43,0,0,1,11.09-57.49,119,119,0,0,1,49.8-49.65c39.06-21,92.72-16.41,126.26,12.19,32.67,27.86,47.47,62,40.06,105-3.44,20-11.15,37.91-24.28,53.32-14.48,17-33,28.39-53.54,36.41-6.14,2.39-8.32,5.89-8.24,12.31C256,361,256.15,415.27,256.15,420.43Zm-65.08-2.17c-.24,13.23-1.25,11,10,11.4,4,.15,5.24-.74,4.89-5q-.63-16.47-.76-33c-.11-20.65-.25-41.3.1-61.94a48.83,48.83,0,0,1,3.09-16c6.24-16.64,18.07-27.72,34.56-34.62a120.46,120.46,0,0,0,25.85-14.8c15.31-11.57,24.3-27.57,23.67-46.84-1.05-32.17-28.3-58.44-60.33-58.61-30.09-.16-61.57,21.92-64.59,51.13-.58,5.6.37,11.54,1.6,17.1.86,3.88,4.5,5.83,8.62,5.16,4.3-.69,3.1-4.45,3.41-7.25.64-5.77.22-11.86,1.94-17.27,9-28.17,43.6-41.64,69.57-27.45,17.59,9.61,26.28,26,23.12,44.76-2.88,17.09-13.92,27.8-28.91,35.15-8.5,4.17-17.6,7.54-25.2,13-19.66,14.13-31.87,32.62-30.78,58.18C191.15,345.13,191.36,402.94,191.07,418.26Zm41.51,67q21.72,0,43.45,0c4.9,0,8.48-3.08,8.44-7.12,0-3.78-2.71-5.77-8.26-5.84-5.16-.07-10.32.1-15.48.12-24.62.11-49.25.15-73.87.38-3.78,0-8.06.47-8.8,5.44-.45,3,4.1,6.45,8.58,7.07a18.78,18.78,0,0,0,2.49.06C203.62,485.37,232.58,485.3,232.58,485.27Zm35.22,32.92c8.26-.31,13.39-4,11.8-11.58a4.76,4.76,0,0,0-3.4-3q-46.71-.7-93.42-.92c-1.22,0-3.34,2-3.52,3.3-.93,6.8,3.17,11.06,10.7,11.15ZM114.18,324.51c-1.8-2.28-3.23-5.62-5.65-6.68-2.18-1-6.17-.32-8.11,1.21a124,124,0,0,0-13.6,13.14c-3.28,3.52-2.55,8,1.4,11.91,3,2.9,6.43,2.9,10.1-.68,4.64-4.51,9-9.34,13.32-14.14C112.59,328.23,113,326.73,114.18,324.51Zm260.94,13.66c-.35-.8-.67-2.6-1.72-3.7-5-5.27-10.24-10.39-15.46-15.48-3.37-3.29-6.51-.92-8.63,1.38-1.4,1.52-2.46,5.53-1.53,6.66,5.2,6.33,10.64,12.61,16.86,17.91C368.33,348.09,374.92,343.86,375.12,338.17ZM105,125.11c2.16-1.62,5.23-2.9,6.46-5.18,1-1.78.54-5.59-.82-7.12-4.17-4.68-9.07-8.7-13.71-13-2.7-2.47-5.58-3-8.4-.28s-4.45,6.26-1.49,9.69c4.11,4.76,8.66,9.16,13.17,13.55C101.18,123.79,102.8,124.1,105,125.11Zm298.62,106.2v0c2.33,0,4.66.08,7,0,5.11-.21,8.57-3.17,8.53-7.22a9.22,9.22,0,0,0-9.1-8.77q-6.24,0-12.48.17c-5.09.11-7.73,2.75-7.79,7.71-.07,5.32,2.45,7.93,7.85,8.08C399.61,231.36,401.61,231.31,403.61,231.31Zm-350.22-.22v0c1.84,0,3.67,0,5.5,0a33.87,33.87,0,0,0,3.47-.42c5.52-.76,7.87-3,7.73-7.2-.15-4.58-3.47-7.76-8.6-7.76-4.66,0-9.32.43-14,.64-5.24.23-7.3,2-7.49,6.6s2.17,7.56,6.87,8.09A58.52,58.52,0,0,0,53.39,231.09ZM347.94,116.7c1.13,1.34,2.68,4.49,5.18,5.7,1.74.85,5.33-.28,7.11-1.74a72.21,72.21,0,0,0,10.78-11c1.39-1.76,2.56-5.41,1.66-6.86a12,12,0,0,0-7.22-5.08C361,96.86,347.87,110.48,347.94,116.7ZM236.86,55.39c0-.66,0-1.33,0-2-.53-7-3.41-10.45-8.47-10.15s-7.89,4.58-7.43,11.51a15.9,15.9,0,0,0,.32,2.47c1.2,5.6,4.09,8.21,8.63,7.82C234.71,64.65,237,61.53,236.86,55.39Z"/>
                    </g>
                </g>
            </svg>
        </div>
        <div class="flex flex-col items-center justify-center">
            <div style="border-radius: 56px; padding: 0.3rem; background: linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)">
                <div class="w-full bg-surface-0 dark:bg-surface-900 py-20 px-8 sm:px-20" style="border-radius: 53px">
                    <div class="text-center mb-8">
                        <svg class="mb-8 w-16 shrink-0 mx-auto" fill="none" viewBox="0 0 54 40" xmlns="http://www.w3.org/2000/svg">
                            <!-- SVG bleibt unverändert -->
                        </svg>
                        <div class="text-surface-900 dark:text-surface-0 text-3xl font-medium mb-4">Willkommen bei QuizShift</div>
                        <span class="text-muted-color font-medium">Logge dich ein um fortzufahren</span>
                    </div>
                    <div>
                        <label class="block text-surface-900 dark:text-surface-0 text-xl font-medium mb-2" for="name1">Name</label>
                        <InputText id="name1" v-model="username" class="w-full md:w-[30rem] mb-8" placeholder="Name" type="text" />

                        <label class="block text-surface-900 dark:text-surface-0 font-medium text-xl mb-2" for="password1">Password</label>
                        <Password id="password1" v-model="password" :feedback="false" :toggleMask="true" class="mb-4" fluid placeholder="Password"></Password>

                        <div class="flex items-center justify-between mt-2 mb-8 gap-8">
                            <div class="flex items-center">
                                <Checkbox id="rememberme1" v-model="checked" binary class="mr-2"></Checkbox>
                                <label for="rememberme1">Merken</label>
                            </div>
                            <span class="font-medium no-underline ml-2 text-right cursor-pointer text-primary">Passwort vergessen</span>
                        </div>
                        <Button class="w-full" label="Sign In" @click="loginUser" />
                        <p v-if="errorStatus" class="text-red-500 mt-4">{{ errorStatus }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.pi-eye {
    transform: scale(1.6);
    margin-right: 1rem;
}

.pi-eye-slash {
    transform: scale(1.6);
    margin-right: 1rem;
}
</style>
