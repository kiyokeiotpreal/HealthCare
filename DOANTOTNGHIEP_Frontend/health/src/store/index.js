import Schedule from '@/pages/Schedule.vue'
import  Axios  from 'axios'
import { createStore } from 'vuex'

export default createStore({
  state: {
    listExam :  [ ],
    serviceName : JSON.parse(sessionStorage.getItem('serviceName')) || "",
    listSche : JSON.parse(sessionStorage.getItem('listSche')) ||  [ ],
    listScheduleByDoctor : {},
    examName : JSON.parse(sessionStorage.getItem('examName')) || "",
    scheduleTime : JSON.parse(sessionStorage.getItem('scheduleTime')) || null,
  },
  getters: {
  },
  mutations: {
    setScheduleByDoctor(state, dataSchedules){
      state.listScheduleByDoctor = dataSchedules;
    },
    setListExam(state, dataExams){
      state.listExam = dataExams
    },
    setListSche(state,dataSches){
      state.listSche = dataSches
    },
    setServiceName(state, Name){
      state.serviceName = Name
    },
    setExamName(state, Name){
      state.examName = Name
    },
    setScheduleTime(state, scheduleTime) {
      state.scheduleTime = scheduleTime;
    }
  },
  actions: {
    async getDataExam({commit}, slugServiceType) {
      try {
        const response = await Axios.get(`http://localhost:8088/api/v1/user/getExaminationService?slugServiceType=${slugServiceType}`);
        commit('setListExam', response.data)
        commit('setServiceName', response.data[0].serviceTypeName)

      } catch (error) {
        alert("error")
      }
    },

    async getDataSche({commit}, speOrExamName) {
      try {
        const response = await Axios.get(`http://localhost:8088/api/v1/user/getDoctorbySpecialist?name=${speOrExamName}`);
        sessionStorage.setItem('listSche', JSON.stringify(response.data));
        sessionStorage.setItem('examName', JSON.stringify(speOrExamName));
        commit('setListSche', response.data);
        commit('setExamName', speOrExamName);
        commit('setServiceName', response.data[0].serviceTypeName)

      } catch (error) {
        alert("DataNotFound")
      }
    },
    async getDataSche2({commit}, examName) {
      try {
        const response = await Axios.get(`http://localhost:8088/api/v1/user/getScheduleByExamName?name=${examName}`);
        sessionStorage.setItem('listSche', JSON.stringify(response.data));
        sessionStorage.setItem('examName', JSON.stringify(response.data.examinationServiceName));
        commit('setListSche', response.data);
        // commit('setExamName', response.data[0].examinationServicename);
        // commit('setServiceName', response.data[0].serviceTypeName)

      } catch (error) {
        alert("DataNotFound2")
      }
    },

    async getScheduleByDoctor({commit}, email) {
      try {
        console.log(email)
        const response = await Axios.get(`http://localhost:8088/api/v1/user/getScheduleByDoctor?email=${email}`);
        commit('setScheduleByDoctor', response.data)

      } catch (error) {
        alert("getListSchedule error")
      }
    },

    

    async saveScheduleTime({commit}, scheduleTime) {
      try {
        // Gọi mutation để cập nhật thông tin thời gian lịch trình vào state
        commit('setScheduleTime', scheduleTime);
        // Lưu thông tin vào sessionStorage
        sessionStorage.setItem('scheduleTime', JSON.stringify(scheduleTime));
      } catch (error) {
        alert("error")
      }
    } 
  },
  modules: {
  }
})
