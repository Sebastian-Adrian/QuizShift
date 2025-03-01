<script setup>

import {ref} from "vue";

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

const addQuestion = () => {
    if (!newQuestion.value.text) {
        alert('Bitte geben Sie eine Frage ein!');
        return;
    }
    quiz.value.questions.push(...newQuestion.value);

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
    <Card style="max-width: 60rem; overflow: hidden">
        <template #title>Quiz erstellen</template>
        <template #content>
            <!-- Quiz Informationen -->
            <div class="field">
                <label for="quiz-title">Titel</label>
                <input id="quiz-title" v-model="quiz.title" placeholder="Titel des Quizzes" type="text" />
            </div>

            <div class="field">
                <label for="quiz-description">Beschreibung</label>
                <textarea
                    id="quiz-description"
                    v-model="quiz.description"
                    placeholder="Beschreibung des Quizzes"
                    rows="3"
                ></textarea>
            </div>

            <hr />

            <!-- Neue Frage hinzufügen -->
            <h3>Neue Frage hinzufügen</h3>
            <div class="field">
                <label for="question-text">Fragetext</label>
                <input
                    id="question-text"
                    v-model="newQuestion.text"
                    placeholder="Fragetext eingeben"
                    type="text"
                />
            </div>

            <div class="field">
                <label for="question-type">Fragetyp</label>
                <select id="question-type" v-model="newQuestion.type">
                    <option value="SINGLE_CHOICE">Single Choice</option>
                    <option value="MULTIPLE_CHOICE">Multiple Choice</option>
                </select>
            </div>

            <!-- Antworten -->
            <h4>Antworten</h4>
            <div class="answers">
                <div
                    v-for="(answer, index) in newQuestion.antworten"
                    :key="index"
                    class="answer-item"
                >
                    <input
                        v-model="answer.text"
                        :placeholder="'Antwort ' + (index + 1)"
                        style="flex: 1;"
                        type="text"
                    />
                    <label>
                        <input
                            :id="'is-correct-' + index"
                            v-model="answer.is_correct"
                            type="checkbox"
                        />
                        Korrekt?
                    </label>
                    <button type="button" @click="removeAnswer(index)">Entfernen</button>
                </div>
            </div>
            <button type="button" @click="addAnswer">Antwort hinzufügen</button>

            <!-- Frage hinzufügen -->
            <button type="button" @click="addQuestion">Frage hinzufügen</button>

            <hr />

            <!-- Bestehende Fragen anzeigen -->
            <h3>Fragen:</h3>
            <div v-if="quiz.questions.length === 0">Noch keine Fragen hinzugefügt.</div>
            <ul v-else>
                <li v-for="(question, qIndex) in quiz.questions" :key="qIndex">
                    <strong>{{ qIndex + 1 }}. {{ question.text }}</strong> ({{ question.type }})
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
