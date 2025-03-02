import { defineStore } from "pinia";
import { ref } from "vue";

export const useQuizStore = defineStore("quizStore", () => {
    const quizzes = ref([]);

    const addQuiz = (quiz) => {
        quizzes.value.push({ ...quiz});
    };

    return {
        quizzes,
        addQuiz,
    };
});
