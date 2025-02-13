<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Benutzername:</label>
        <input
          type="text"
          id="username"
          v-model="username"
          required
          placeholder="Benutzernamen eingeben"
        />
      </div>

      <div class="form-group">
        <label for="password">Passwort:</label>
        <input
          type="password"
          id="password"
          v-model="password"
          required
          placeholder="Passwort eingeben"
        />
      </div>

      <button type="submit">Anmelden</button>
    </form>

    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from "axios";
import api from "@/api/api.js";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      errorMessage: "",
    };
  },
  methods: {
    /**
     * Führt den Login-Vorgang durch und speichert ggf. ein Authentifizierungs-Token.
     */
    async login() {
      try {
        // API-Aufruf zur Anmeldung
        const response = api.post("auth/login", {
          username: this.username,
          password: this.password,
        });

        // Authentifizierung erfolgreich
        const token = response.data.token; // JWT-Token aus Backend
        localStorage.setItem("authToken", token); // Speichert Token im localStorage

        // Token wird als Standard-Header für alle zukünftigen Anfragen gesetzt
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

        // Weiterleitung nach erfolgreichem Login (z. B. zur Quiz-Seite)
        this.$router.push({ name: "Quiz" });
      } catch (error) {
        // Fehlermeldung anzeigen, wenn die Authentifizierung fehlschlägt
        this.errorMessage = "Anmeldung fehlgeschlagen. Bitte versuchen Sie es erneut.";
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #264e6d;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.error-message {
  color: red;
  margin-top: 15px;
}
</style>
