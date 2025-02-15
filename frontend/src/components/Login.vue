
<script>
import axios from "axios";
import api from "@/api/api.js";
import {InputText} from "primevue";

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
    async login() {
      try {
          // API-Aufruf zur Anmeldung
        const response = await api.post("auth/login", {
          username: this.username,
          password: this.password,
        });

          // Authentifizierung erfolgreich
        const token = response.data; // JWT-Token aus Backend
        localStorage.setItem("authToken", token); // Speichert Token im localStorage
        // Token wird als Standard-Header für alle zukünftigen Anfragen gesetzt
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

        // Weiterleitung nach erfolgreichem Login
        this.$router.push({ name: "home" });
      } catch (error) {
        // Fehlermeldung anzeigen, wenn die Authentifizierung fehlschlägt
        this.errorMessage = "Anmeldung fehlgeschlagen. Bitte versuchen Sie es erneut."
        console.error(error);
      }
    },
},
};
</script>
<template>

  <div class="login-container">
    <Card header="Login">
      <Form
        v-slot="{ values, errors, handleSubmit, reset }"
        :onSubmit.prevent="handleSubmit(login)"
        class="p-fluid"
      >
        <div class="field">
          <label for="username">Benutzername</label>
          <InputText
            id="username"
            v-model="values.username"
            placeholder="Benutzername eingeben"
            required
          />
          <Message
            v-if="errors.username"
            :text="errors.username"
            severity="error"
          />
        </div>

        <div class="field">
          <label for="password">Passwort</label>
          <Password
            id="password"
            v-model="values.password"
            feedback
            placeholder="Passwort eingeben" />
          <Message
            v-if="errors.password"
            :text="errors.password"
            severity="error"
          />
        </div>

        <Button class="mt-3" label="Anmelden" type="submit" />
      </Form>

      <p v-if="errorMessage" class="error-message mt-3">
        {{ errorMessage }}
      </p>
    </Card>
  </div>

  <Form v-slot="$form" :initialValues :resolver class="flex flex-col gap-4 w-full sm:w-56" @submit="onFormSubmit">
    <div class="flex flex-col gap-1">
      <InputText fluid name="username" placeholder="Username" type="text" />
      <Message v-if="$form.username?.invalid" severity="error" size="small" variant="simple">{{ $form.username.error?.message }}</Message>
    </div>
    <Button label="Submit" severity="secondary" type="submit" />
  </Form>


  <div class="login-container">
    <h2>Login</h2>
    <br>
    <form @submit.prevent="login">
      <div class="form-group">
<!--        <label for="username">Benutzername:</label>
        <input
          type="text"
          id="username"
          v-model="username"
          required
          placeholder="Benutzernamen eingeben"
        />-->
        <FloatLabel variant="on">
          <InputText id="username" v-model="username" required type="text">
          </InputText>
          <label for="username">Username</label>
        </FloatLabel>
      </div>
      <br>
      <div class="form-group">
<!--        <label for="password">Passwort:</label>
        <input
          type="password"
          id="password"
          v-model="password"
          required
          placeholder="Passwort eingeben"
        />-->
        <FloatLabel variant="on">
          <InputText id="password" v-model="password" required type="password">
          </InputText>
          <label for="password">Passwort</label>
        </FloatLabel>
      </div>

      <button type="submit">Anmelden</button>
    </form>

    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>
