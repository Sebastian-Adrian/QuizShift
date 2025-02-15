
<script>
import axios from "axios";
import api from "@/api/api.js";
import {InputText} from "primevue";
import {zodResolver} from '@primevue/forms/resolvers/zod';
import {z} from 'zod';
import {Form} from "@primevue/forms";

export default {
name: "Login",
data() {
  return {
      username: "",
      password: "",
      errorMessage: "",
      resolver: zodResolver(z.object({
        username: z.string().min(3, { message: "Username muss mindestens 3 Zeichen lang sein" }),
        password: z.string().min(3, { message: "Passwort muss mindestens 3 Zeichen lang sein" }),
      }))
    };
},
methods: {

  onFormSubmit(valid) {
    if (valid) {
      this.$toast.add({
        severity: "success",
        summary: "Success Message",
        detail: "Anmeldung erfolgreich",
        life: 3000
      });
    }
  },

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
  <div class="card flex justify-center">
    <Toast />
    <Form :resolver class="flex flex-col gap-4 w-full sm:w-56" @submit="onFormSubmit">
      <FormField v-slot="$field" as="section" class="flex flex-col gap-2" initialValue="" name="username">
        <InputText placeholder="Username" type="text" />
        <Message v-if="$field?.invalid" severity="error" size="small" variant="simple">{{ $field.error?.message }}</Message>
      </FormField>
      <FormField v-slot="$field" asChild initialValue="" name="password">
        <section class="flex flex-col gap-2">
          <Password :feedback="false" fluid placeholder="Password" toggleMask type="text" />
          <Message v-if="$field?.invalid" severity="error" size="small" variant="simple">{{ $field.error?.message }}</Message>
        </section>
      </FormField>
      <Button label="Submit" severity="secondary" type="submit" />
    </Form>
  </div>
</template>
