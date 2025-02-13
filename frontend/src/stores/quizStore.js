import { defineStore } from 'pinia';
//import api from '../api/api.js';
import axios from 'axios';

export const useQuizStore = defineStore('quiz', {
  state: () => ({
    quizzes: [],
  }),
  actions: {
    async fetchQuizzes() {
      /*
      const response = await api.get('/quizzes');
       */
      console.log('fetching quizzes');
      try {
        const response = await axios.get('/api/quizzes');
        this.quizzes = response.data;
        console.log(this.quizzes)
      } catch (e) {
        console.error('Fehler beim Laden der Quiz-Daten:', e);
      }
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
