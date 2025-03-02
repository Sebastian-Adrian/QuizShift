<script setup>

import {ref} from "vue";
import IftaLabel from 'primevue/iftalabel';
import Select from 'primevue/select';

const quiz = ref({
    title: '',
    description: '',
    questions: []
});

const newQuestion = ref({
    text: '',
    type: 'SINGLE_CHOICE',
    answers: [{
        text: '',
        isCorrect: false,
    }]
});

const questionType = ref([
    {name: 'Single Choice', value: 'SINGLE_CHOICE'},
    {name: 'Multiple Choice', value: 'MULTIPLE_CHOICE'},
])

const addQuestion = () => {
    if (!newQuestion.value.text) {
        alert('Bitte geben Sie eine Frage ein!');
        return;
    }
    quiz.value.questions.push(newQuestion.value);

    newQuestion.value = {
        text: '',
        type: 'SINGLE_CHOICE',
        answers: [{
            text: '',
            isCorrect: false,
        }]
    };
};

const addAnswer = () => {
    newQuestion.value.answers.push({
        text: '',
        isCorrect: false,
    });
}

const removeAnswer = (index) => {
    newQuestion.value.answers.splice(index, 1);

};

const saveQuiz = async () => {
    console.log(quiz.value);
}


</script>

<template>
    <Card style="max-width: 40rem; overflow: hidden">
        <template #title>

        </template>
        <template #content>
            <div class="flex flex-col gap-4">
                <div class="flex-grow">
                    <IftaLabel>
                        <label for="quiz-title">Titel</label>
                        <InputText id="quiz-title" v-model="quiz.title" class="w-full" placeholder="Titel des Quizzes" type="text"/>
                    </IftaLabel>
                </div>

                <IftaLabel>
                    <label for="quiz-description">Beschreibung</label>
                    <Textarea
                        id="quiz-description"
                        v-model="quiz.description"
                        class="w-full"
                        placeholder="Beschreibung des Quizzes"
                        rows="3"
                    ></Textarea>
                </IftaLabel>
                <hr/>

                <!-- Neue Frage hinzufügen -->
                <h2>Neue Frage hinzufügen</h2>
                <IftaLabel>
                    <label for="question-text">Fragetext</label>
                    <InputText
                        id="question-text"
                        v-model="newQuestion.text"
                        class="w-full"
                        placeholder="Fragetext eingeben"
                        type="text"
                    />
                </IftaLabel>

                <IftaLabel>
                    <Select v-model="newQuestion.type" :options="questionType" class="w-full md:w-56"
                            option-value="value" optionLabel="name" placeholder="Fragetyp auswählen">
                    </Select>
                </IftaLabel>
            </div>
            <div class="flex flex-col gap-4">
                <!-- Antworten -->
                <h2>Antworten</h2>
                <div class="answers">
                    <div
                        v-for="(answer, index) in newQuestion.answers"
                        :key="index"
                        class=""
                    >
                        <InputText
                            v-model="answer.text"
                            :placeholder="'Antwort ' + (index + 1)"
                            style="flex: 1;"
                            type="text"
                        />
                        <ToggleButton
                            v-model="answer.is_correct"
                            class=""
                            offIcon="pi pi-times"
                            offLabel="Falsch"
                            onIcon="pi pi-check"
                            onLabel="Korrekt"
                            style="width: 6rem"
                        />
                        <Button aria-label="danger" icon="pi pi-times" severity="danger" size="small"
                                @click="removeAnswer(index)"/>
                    </div>
                </div>
            </div>
            <Button type="button" @click="addAnswer">Antwort hinzufügen</Button>
            <Button type="button" @click="addQuestion">Frage hinzufügen</Button>

            <hr/>


            <!-- Bestehende Fragen anzeigen -->
            <h2>Fragen:</h2>
            <div v-if="quiz.questions.length === 0">Noch keine Fragen hinzugefügt.</div>
            <ul v-else>
                <li v-for="(question, qIndex) in quiz.questions" :key="qIndex">
                    {{ question }}
                    <strong>{{ qIndex + 1 }}. {{ question.text }} - {{ question.type.type }}</strong>
                    <ul>
                        <li v-for="(answer, aIndex) in question.antworten" :key="aIndex">
                            {{ answer.text }} - <strong>{{ answer.is_correct ? 'Korrekt' : 'Falsch' }}</strong>
                        </li>
                    </ul>
                </li>
            </ul>
        </template>

        <template #footer>
            <div class="flex gap-4 right-0">
                <Button label="Abbrechen" raised severity="danger" />
                <Button label="Speichern" raised severity="success" @click="saveQuiz" />
            </div>
        </template>
    </Card>

</template>

<style lang="scss" scoped>

</style>
