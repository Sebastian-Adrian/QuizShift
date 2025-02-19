import { createApp } from 'vue';
import App from './App.vue';
import index from './router';

import Aura from '@primevue/themes/aura';
import PrimeVue from 'primevue/config';
import ConfirmationService from 'primevue/confirmationservice';
import ToastService from 'primevue/toastservice';
import {createPinia} from 'pinia';

import '@/assets/styles.scss';
import '@/assets/tailwind.css';

const app = createApp(App);

app.use(index);
app.use(PrimeVue, {
    theme: {
        preset: Aura,
        options: {
            darkModeSelector: '.app-dark'
        }
    }
});
app.use(ToastService);
app.use(ConfirmationService);
app.use(createPinia())

app.mount('#app');
