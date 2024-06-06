import  Axios  from 'axios'
import { createStore } from 'vuex'

const user = JSON.parse(localStorage.getItem('user'));

export default createStore({
  state: {
    initialState: user || {},

    listDoctor : [],
    listSchedule : []
  },
  getters: {
  },
  mutations: {
    setListDoctor(state, dataDoctors){
      state.listDoctor = dataDoctors
    },
    setListSchedule(state, dataSchedules){
      state.listSchedule = dataSchedules
    },
  },
  actions: {
    async getDataDoctor({commit}) {
      try {
        const response = await Axios.get(`http://localhost:8088/api/v1/user/getAllDoctor`);
        commit('setListDoctor', response.data)

      } catch (error) {
        alert("getListDoctor error")
      }
    },
    async getDataSchedule({commit}, email) {
      try {
        const response = await Axios.get(`http://localhost:8088/api/v1/user/getScheduleByDoctor?email=${email}`);
        commit('setListSchedule', response.data)

      } catch (error) {
        alert("getListSchedule error")
      }
    },
    
  },
  modules: {
  }
})
