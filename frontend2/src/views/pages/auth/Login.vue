<script setup>
import FloatingConfigurator from '@/components/FloatingConfigurator.vue';
import { ref } from 'vue';
import api from "@/api/api";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router"; // Pinia Store für Authentifizierung

// Datenfelder für Eingaben
const username = ref('');
const password = ref('');
const checked = ref(false); // Für den "Merken"-Checkbox

// Fehlerstatus
const error = ref(null);

// Router-Instanz
const router = useRouter();

// Zugriff auf den Auth-Store
const authStore = useAuthStore();

// Funktion zur Anmeldung
const loginUser = async () => {
    try {
        error.value = null; // Fehler zurücksetzen
        const response = await api.post('/auth/login', {
            username: username.value,
            password: password.value,
        });

        // Erfolgreiche Anmeldung
        const { token } = response.data;
        authStore.setToken(token); // Token im Store speichern
        authStore.setUser({
            email: username.value,
        });

        // Weiterleitung (z. B. zur Startseite)
        router.push("/");
    } catch (err) {
        // Fehlerbehandlung
        if (err.response && err.response.status === 401) {
            error.value = "Ungültige Anmeldedaten.";
        } else {
            error.value = "Ein Fehler ist aufgetreten. Bitte später erneut versuchen.";
        }
    }
};
</script>

<template>
    <FloatingConfigurator />
    <div class="bg-surface-50 dark:bg-surface-950 flex items-center justify-center min-h-screen min-w-[100vw] overflow-hidden">
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
                        <p v-if="error" class="text-red-500 mt-4">{{ error }}</p>
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
