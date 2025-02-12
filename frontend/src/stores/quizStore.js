import { defineStore } from 'pinia';
import api from '../api/api.js';

export const useQuizStore = defineStore('quiz', {
  state: () => ({
    quizzes: [],
  }),
  actions: {
    async fetchQuizzes() {
      const response = await api.get('/quizzes');
      this.quizzes = response.data;
    },
    async createQuiz(quiz) {
      if (
        !quiz ||
        typeof quiz !== 'object' ||
        typeof quiz.title !== 'string' ||
        !quiz.title.trim() ||
        !Array.isArray(quiz.questions) ||
        quiz.questions.length === 0
      ) {
        throw new Error('Ungültige Quiz-Daten');
      }
      try {
        const response = await api.post('/quizzes', quiz);
        if (!Array.isArray(this.quizzes)) {
          this.quizzes = [];
        }
        this.quizzes.push(response.data);
        return response.data;
      } catch (error) {
        console.error('Fehler beim Erstellen des Quiz:', error);
        throw error;
      }
    }
  },
});
