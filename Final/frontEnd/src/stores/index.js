import { createStore } from 'vuex';

export default createStore({
    state() {
        const today = new Date();
        const tomorrow = new Date(today);
        tomorrow.setDate(today.getDate() + 1);
        return {
            isAuthenticated: false,
            user: null,
            date: [today, tomorrow],
            city: '南京'
        };
    },
    mutations: {
        setAuthenticated(state, isAuthenticated) {
            state.isAuthenticated = isAuthenticated;
        },
        setUser(state, user) {
            state.user = user;
        },
        setDate(state, date) {
            state.date = date;
        },
        setCity(state, city) {
            state.city = city;
        }
    },
    actions: {
        login({ commit }, user) {
            commit('setAuthenticated', true);
            commit('setUser', user);
        },
        logout({ commit }) {
            commit('setUser', null);
            commit('setAuthenticated', false);
        },
        search({ commit }, { date, city }) {
            commit('setDate', date);
            commit('setCity', city);
        }
    },
    getters: {
        isAuthenticated: state => state.isAuthenticated,
        user: state => state.user,
        date: state => state.date,
        city: state => state.city
    }
});
