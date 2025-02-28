<script setup>
import { onMounted, ref } from 'vue';
import api from '@/api/api';

const quiz = ref({ title: '', description: '', id: '', questions: [] });
const currentQuestionIndex = ref(0);
const quizList = ref({ title: '', description: '', id: '', questions: [] });

onMounted(async () => {
    try {
        const token = localStorage.getItem('token');
        if (!token) {
            throw new Error('Benutzer nicht authentifiziert');
        }

        const response = await api.get('/quiz/details', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        const data = response.data[0];
        console.log(data);
        quiz.value = {
            id: data.id,
            title: data.title,
            description: data.description,
            questions: data.questions
        };
    } catch (error) {
        console.error('Fehler beim Abrufen der Daten:', error);
    }
});

const nextQuestion = () => {
    if (currentQuestionIndex.value < quiz.value.questions.length - 1) {
        currentQuestionIndex.value++;
    } else {
        console.log('Quiz beendet');
    }
};
</script>

<template>
    <Card v-if="quiz.questions.length > 0" style="max-width: 60rem; overflow: hidden">
        <template #header>
            <img alt="user header" src="https://primefaces.org/cdn/primevue/images/usercard.png" />
        </template>
        <template #title>{{ quiz.title }}</template>
        <template #content>
            {{ quiz.description }}
            <br />
            <p class="m-0">
                {{ quiz.questions[currentQuestionIndex].text }}
            </p>
            <div class="toggle">
                <div v-for="(answer, index) in quiz.questions[currentQuestionIndex].antworten" :key="index" >
                    <input :id="'answer-' + index" :name="'question-' + currentQuestionIndex" :value="answer.text" type="radio"/>
                    <label :for="'answer-' + index">{{ answer.text }}</label>
                </div>
            </div>
        </template>
        <template #footer>
            <div class="flex gap-4 right-0">
                <Button label="Abbrechen" raised severity="danger" />
                <Button label="OK" raised severity="success" />
            </div>
        </template>
    </Card>
</template>

<style lang="scss" scoped>
$open-sans: 'Open Sans', 'Helvetica', sans-serif;

$darkNavy: #213140;
$teal1: #66b3fb;
$teal2: rgba(75, 157, 234, 0.5);
$charcoal: #555555;
$gold: #b6985a;

$activeShadow: 0 0 10px rgba($teal1, 0.5);

@mixin focusOutline {
    outline: dotted 1px #ccc;
    outline-offset: 0.45rem;
}
@mixin hideInput {
    width: 0;
    height: 0;
    position: absolute;
    left: -9999px;
}
@mixin breakpoint($point) {
    @if $point == 1100 {
        @media (max-width: 1100px) {
            @content;
        }
    } @else if $point == 800 {
        @media (max-width: 800px) {
            @content;
        }
    }
}

.toggle {
    counter-reset: toggle-counter; /* Startet Zähler bei 1 */
}

.toggle {
    margin: 1.5rem 0 1.5rem;
    box-sizing: border-box;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    gap: 1rem;

    div {
        max-height: 5rem;
        flex: 0 0 calc(50% - 0.5rem);

        input + label {
            margin-right: 0;
            padding: 0.75rem 1rem;
            box-sizing: border-box;
            position: relative;
            display: flex;
            align-items: center;
            border: solid 1px #ddd;
            box-shadow: 0 0 0 rgba(255, 255, 255, 0);
            transition:
                border-color 0.15s ease-out,
                color 0.25s ease-out,
                background-color 0.15s ease-out,
                box-shadow 0.15s ease-out;
            border-radius: 6px;
            font-size: 1rem;
            line-height: 1.2;
            font-weight: 600;
            text-align: left;
            /* Textfluss ermöglichen */
            white-space: normal; /* Standardmäßig ist dies "nowrap", kann also geändert werden */
            word-wrap: break-word; /* Für ältere Browser */
            word-break: break-word; /* Lange Wörter brechen bei Bedarf */
            overflow-wrap: break-word;


        }
    }

    input {
        @include hideInput;
    }

    input:hover + label {
        border-color: $darkNavy;
    }
    input:checked + label {
        background-color: $teal2;
        color: #fff;
        box-shadow: $activeShadow;
        border-color: $teal2;
        z-index: 1;
    }
    input:focus + label {
        @include focusOutline;
    }

    label::before {
        content: counter(toggle-counter);
        counter-increment: toggle-counter; /* Zählt Labels hoch */
        font-weight: bold;
        margin-right: 0.5rem;
        padding: 0.4rem 0.8rem;
        color: #5988bf; /* Nummernfarbe */
        background-color: $teal2;
        border: solid 1px #ddd;
        border-radius: 5px;
    }

    /* Ersetzt die Nummer durch ein Icon, wenn ausgewählt */
    input:checked + label::after {
        content: '\2713'; /* Unicode für ein Häkchen-Icon */
        color: white;
        background-color: $teal2;
        padding: 0.1rem 0.3rem;
        margin-right: 1rem;
        border-radius: 20%;
        font-size: 1rem;
        display: inline-block;
        width: 1.5rem;
        height: 1.5rem;
        position: absolute;
        right: 0; /* Ganz rechts */
        top: 50%; /* Zentriert zur Vertikalen */
        transform: translateY(-50%); /* Für exakte Zentrierung */
    }

    @include breakpoint(800) {
        input + label {
            padding: 0.75rem 0.25rem;
            flex: 0 0 50%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    }
}
</style>
