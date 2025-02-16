import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem('token') || null, // Token speichern
        user: null as any, // Benutzerdaten
    }),
    actions: {
        // Token setzen
        setToken(token: string) {
            this.token = token;
            localStorage.setItem('token', token);
        },

        // Benutzerdaten speichern
        setUser(user: any) {
            this.user = user;
        },

        // Abmelden
        logout() {
            this.token = null;
            this.user = null;
            localStorage.removeItem('token');
        },
    },
    getters: {
        // Authentifizierungsstatus
        isAuthenticated: (state) => !!state.token,
    },
});
